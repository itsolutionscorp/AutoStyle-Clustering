'''
Excercism.io Problem #1
'''

def hey(test_str):
    if test_str.endswith('?') and not test_str.isupper():
        return 'Sure.'
    elif test_str.isupper():
        return 'Whoa, chill out!'
    elif test_str == '' or test_str.endswith('\t'):
		return 'Fine. Be that way!'
    else:
        return 'Whatever.'
    
def main():
	pass
    
if __name__ == '__main__':
    main()
