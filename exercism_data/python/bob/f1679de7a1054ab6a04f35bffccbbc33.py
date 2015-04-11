def hey(text):
    responses = { "question" : "Sure." , "yell" : "Whoa, chill out!", "nothing": "Fine. Be that way!", "default" : "Whatever."}
    if is_yell(text):
        answer = "yell"
    elif is_question(text):
        answer = "question"
    elif is_empty(text):
        answer="nothing"
    else:
        answer="default"

    return responses[answer]



def is_yell(text):
    if text.isupper() is True: 
        return True
    return False

def is_empty(text):
    return text.isspace() or text=='' 

def is_question(text):
    return text.endswith("?")
