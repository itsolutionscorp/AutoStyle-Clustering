def hey(question):
    output = ''

    if question.strip() == '' :
        output = 'Fine. Be that way!'

    elif question.lower() != question and question.upper() == question :
        output = 'Whoa, chill out!'

    elif question.find('?') == len(question) -1  :
        output = 'Sure.'
    else :
        output = 'Whatever.'
    return output
