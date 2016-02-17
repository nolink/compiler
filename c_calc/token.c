
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "token.h"

static char* current_line;
static int idx;

void set_line(char* line){
	current_line = line;
	idx = 0;
}

void get_token(Token* token){

	char current_char = '\0';
	int token_str_idx = 0;
	TokenStatus status = INIT_STATUS;
	token->kind = BAD_TOKEN;

	while(current_line[idx] != '\0'){

		if(idx >= MAX_TOKEN_SIZE){
			printf("token too long\n");
			exit(1);
		}

		current_char = current_line[idx];

		if(!isdigit(current_char) && current_char != '.' && 
			(status == IN_INT_STATUS || status == IN_FRAC_STATUS)){
			token->kind = NUMBER_TOKEN;
			sscanf(token->str, "%lf", &(token->val));
			return;
		}

		if(status == INIT_STATUS && isspace(current_char)){
			idx++;
			continue;
		}

		token->str[token_str_idx++] = current_char;
		token->str[token_str_idx] = '\0';
		idx++;

		if(status == INIT_STATUS){
			if(isdigit(current_char)){
				status = IN_INT_STATUS;	
				continue;
			}else if(current_char == '.'){
				printf("\".\" is only allowed after number");
				return;
			}else{
				switch(current_char){
					case '+':
						token->kind = ADD_OP_TOKEN;
						return;
					case '-':
						token->kind = SUB_OP_TOKEN;
						return;
					case '*':
						token->kind = MUL_OP_TOKEN;
						return;
					case '/':
						token->kind = DIV_OP_TOKEN;
						return;
					case '(':
						token->kind = LEFT_BRACKET_TOKEN;
						return;
					case ')':
						token->kind = RIGHT_BRACKET_TOKEN;
						return;
				}
			}
		}else if(status == IN_INT_STATUS){
			if(current_char == '.'){
				status = IN_DOT_STATUS;
			}
			continue;
		}else if(status == IN_DOT_STATUS){
			if(isdigit(current_char)){
				status = IN_FRAC_STATUS;
				continue;
			}else{
				printf("only number is allowed after \".\"");
				return;
			}
		}
	}

	if(status == IN_INT_STATUS || status == IN_FRAC_STATUS){
		token->kind = NUMBER_TOKEN;
		sscanf(token->str, "%lf", &(token->val));
	}else{
		token->kind = EOL_TOKEN;
	}
}

/*
int main(int argc, char const *argv[])
{
	
	printf("enter expressions: \n");

	char buf[1024];
	Token token;
	if(gets(buf)){
		printf("will parse: %s\n", buf);
		set_line(buf);
		while(1){
			get_token(&token);
			if(token.kind == EOL_TOKEN || token.kind == BAD_TOKEN){
				break;
			}
			if(token.kind == NUMBER_TOKEN){
				printf("source str: %s, val: %lf\n", token.str, token.val);
			}else{
				printf("source str: %s, current index: %d\n", token.str, idx);
			}
		}
	}
	return 0;
}
*/