import string


RESP = {
    "Wh": "Whatever.",
    "F" : "Fine. Be that way!",
    "S" : "Sure.",
    "Wo": "Whoa, chill out!"
}


def is_silence(sal):
    return sal.strip() == ""


def is_shouting(sal):
    return sal.isupper()


def is_question(sal):
    return sal.endswith("?")


def hey(sal):
    sal = sal.strip()

    if is_silence(sal):
        return RESP["F"]
    elif is_shouting(sal):
        return RESP["Wo"]
    elif is_question(sal):
        return RESP["S"]
    else:
        return RESP["Wh"]
