def hey(conversation):
    if conversation.isupper():
        return "Woah, chill out!"
    elif conversation.endswith("?"):
        return "Sure."
    elif "".join(set(conversation)) == " " or conversation == "":
        return "Fine. Be that way!"
    else:
        return "Whatever."
