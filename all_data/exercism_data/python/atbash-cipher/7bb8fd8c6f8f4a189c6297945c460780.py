def encode(phrase):
	phrase = ''.join([x.lower() for x in phrase if x.isalnum()])
	all = 'abcdefghijklmnopqrstuvwxyz'
	raw_result = ''.join([all[-all.index(i)-1] if i in all else i for i in phrase])
	result = ''.join([' '+raw_result[x] if x%5==0 and x!=0 else raw_result[x] for x in range(len(raw_result))])
	return result
	
def decode(code):
	code = ''.join([x for x in code if x.isalnum()])
	all = 'abcdefghijklmnopqrstuvwxyz'
	return ''.join([all[-all.index(i)-1] if i in all else i for i in code])
	
