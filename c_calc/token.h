#ifndef CALC_TOKEN_HEADER
#define CALC_TOKEN_HEADER

typedef enum {  

	BAD_TOKEN,
	NUMBER_TOKEN,
	ADD_OP_TOKEN,
	SUB_OP_TOKEN,
	MUL_OP_TOKEN,
	DIV_OP_TOKEN,
	EOF_TOKEN

} TokenKind;

typedef enum {

	INIT_STATUS,
	IN_INT_STATUS,
	IN_DOT_STATUS,
	IN_FRAC_STATUS

} TokenStatus;

typedef struct 
{
	
	char* str;
	TokenKind kind;
	double val;

} Token;

#endif