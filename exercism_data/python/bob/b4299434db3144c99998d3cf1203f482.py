def hey(st):
    answer = ''
    if st.isspace() or not st:
        answer = 'Fine. Be that way!'
    elif st.isupper():
        answer = 'Whoa, chill out!'
    elif st[-1] == '?':
        answer = 'Sure.'
    else:
        answer = 'Whatever.'
    return answer
