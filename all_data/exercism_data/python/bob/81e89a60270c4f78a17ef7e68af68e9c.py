"""Bob: A Surly Teenager Simulator """

def _is_yell(request):
    """Checks to see if Bob's being yelled at.
       Yells are ALL UPPERCASE.
    """
    return request.isupper()


def _is_question(request):
    """Checks to see if Bob's being asked something.
       Anything ending in a question mark is a question.
    """
    return request.endswith('?')


def _is_nothing(request):
    """Checks to see if anything was actually said"""
    return request == None or request.strip() == ''


def hey(request):
    """Generate Bob's replies to whatever's told to him"""
    if _is_nothing(request):
        return 'Fine. Be that way!'
    # Yells are more impactful than questions,
    # so a yelled question gets a yell response
    if _is_yell(request):
        return 'Woah, chill out!'
    if _is_question(request):
        return 'Sure.'
    return 'Whatever.'
