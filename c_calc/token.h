#ifndef CALC_TOKEN_HEADER
#define CALC_TOKEN_HEADER

#define MAX_TOKEN_SIZE (100)

typedef enum {  

	BAD_TOKEN,
	NUMBER_TOKEN,
	ADD_OP_TOKEN,
	SUB_OP_TOKEN,
	MUL_OP_TOKEN,
	DIV_OP_TOKEN,
	EOL_TOKEN

} TokenKind;

typedef enum {

	INIT_STATUS,
	IN_INT_STATUS,
	IN_DOT_STATUS,
	IN_FRAC_STATUS

} TokenStatus;

typedef struct 
{
	
	char str[MAX_TOKEN_SIZE];
	TokenKind kind;
	double val;

} Token;


void set_line(char* buf);
void get_token(Token* token);

#endif