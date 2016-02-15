
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ctype.h"
#include "token.h"

static char* current_line;
static int idx;

void set_line(char* line){
	current_line = line;
	idx = 0;
}

int valid_char(char c){
	return isspace(c) || isdigit(c) || c == '+' || c == '-' || c == '*' || c == '/' || c == '.';
}

void get_token(Token* token){

	char current_char = '\0';
	/*token->str = (char*)malloc(sizeof(char)*strlen(current_line));*/
	int token_str_idx = 0;
	TokenStatus status = INIT_STATUS;
	token->kind = BAD_TOKEN;

	while(current_line[idx] != '\0'){
		current_char = current_line[idx];

		if(!valid_char(current_char)){
			printf("invalid char at index %d\n", idx);
			return;
		}

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
						token->kind = DIV_OP_TOKEN;
						return;
					case '/':
						token->kind = DIV_OP_TOKEN;
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
		token->kind = EOF_TOKEN;
	}
}


int main(int argc, char const *argv[])
{
	
	printf("enter expressions: \n");

	char* buf = (char*)malloc(sizeof(char)*100);
	Token* token;
	token = (Token*)malloc(sizeof(Token));
	if(gets(buf)){
		printf("will parse: %s\n", buf);
		set_line(buf);
		token->str = (char*)malloc(sizeof(char)*strlen(buf));
		while(1){
			get_token(token);
			if(token->kind == EOF_TOKEN || token->kind == BAD_TOKEN){
				break;
			}
			if(token->kind == NUMBER_TOKEN){
				printf("source str: %s, val: %lf\n", token->str, token->val);
			}else{
				printf("source str: %s, current index: %d\n", token->str, idx);
			}
		}
		free(token->str);
	}
	free(token);
	free(buf);
	return 0;
}
