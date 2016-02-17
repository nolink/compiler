
#include <stdio.h>
#include <stdlib.h>
#include "token.h"

Token pre_read_token;
int is_pre_read_exists;

void my_get_token(Token* token){
	if(is_pre_read_exists){
		*token = pre_read_token;
		is_pre_read_exists = 0;
	}else{
		get_token(token);
	}
}

void unget_token(Token* token){
	pre_read_token = *token;
	is_pre_read_exists = 1;
}

double parse_expr();

double parse_primary_expr(){
	Token token;
	my_get_token(&token);

	int is_reverse = 0;
	if(token.kind == SUB_OP_TOKEN){
		is_reverse = 1;
		my_get_token(&token);
	}

	if(token.kind == NUMBER_TOKEN){
		return is_reverse ?  -token.val : token.val;
	}else if(token.kind == LEFT_BRACKET_TOKEN){
		double v = parse_expr();
		my_get_token(&token);
		if(token.kind != RIGHT_BRACKET_TOKEN){
			printf("synctax error, can not find ')', found %s instead\n", token.str);
			exit(1);
		}
		return is_reverse ? -v : v;
	}else if(token.kind != EOL_TOKEN){
		printf("synctax error\n");
	}

	return 0.0;
}

double parse_term(){
	double v1;
	double v2;
	Token token;
	v1 = parse_primary_expr();

	while(1){
		my_get_token(&token);
		if(token.kind != MUL_OP_TOKEN && token.kind != DIV_OP_TOKEN){
			unget_token(&token);
			break;
		}else{
			v2 = parse_primary_expr();
			if(token.kind == MUL_OP_TOKEN){
				v1 *= v2;
			}else if(token.kind == DIV_OP_TOKEN){
				v1 /= v2;
			}
		}
	}
	return v1;
}

double parse_expr(){
	double v1;
	double v2;
	Token token;
	
	v1 = parse_term();
	while(1){
		my_get_token(&token);
		if(token.kind != ADD_OP_TOKEN && token.kind != SUB_OP_TOKEN){
			unget_token(&token);
			break;
		}else{
			v2 = parse_term();
			if(token.kind == ADD_OP_TOKEN){
				v1 += v2;
			}else if(token.kind == SUB_OP_TOKEN){
				v1 -= v2;
			}
		}
	}
	return v1;
}

int main(int argc, char const *argv[])
{
	
	printf("enter expressions: \n");

	char buf[1024];
	if(gets(buf)){
		set_line(buf);
		printf("%lf\n", parse_expr());
	}

	return 0;
}