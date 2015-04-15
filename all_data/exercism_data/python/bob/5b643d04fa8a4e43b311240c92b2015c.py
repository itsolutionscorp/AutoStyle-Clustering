def hey(text):
    responses = { "question" : "Sure." , "yell" : "Whoa, chill out!", "nothing": "Fine. Be that way!", "default" : "Whatever."}
    if text.isupper():
        answer = "yell"
    elif text.endswith("?"):
        answer = "question"
    elif text.isspace() or text=="":
        answer="nothing"
    else:
        answer="default"

    return responses[answer]
