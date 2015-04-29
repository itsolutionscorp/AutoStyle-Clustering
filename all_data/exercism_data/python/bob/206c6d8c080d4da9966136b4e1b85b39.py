'''
Excercism.io Problem #1
'''

def hey(test_str):
    if test_str.isupper():
        return 'Whoa, chill out!'
    if test_str.endswith('?'): 
        return 'Sure.'
    if test_str.isspace() or len(test_str) < 1:
	    return 'Fine. Be that way!'
    return 'Whatever.'
    
def main():
	pass
    
if __name__ == '__main__':
    main()
