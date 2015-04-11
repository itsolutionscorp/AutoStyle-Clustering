# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    answer = "Whatever."
    answerDic = {
        'question': 'Sure.',
        'yell': 'Whoa, chill out!',
        'silent': 'Fine. Be that way!',
    }
    what = what.strip()
    if what.isupper():
        answer = answerDic['yell']
    elif what.endswith('?'):
        answer = answerDic['question']
    elif what == '':
        answer = answerDic['silent']

    return answer
