import re
def hey( input_str ):
    """Analyze input and return appropriate response"""
    ends_with_a_q_re = re.compile("\?$")
    no_stmt_re       = re.compile("^\s+$")
    word_list        = input_str.split()
    upper_count      = 0
    not_upper_count  = 0

    for word in word_list:
        if word.isupper():
            upper_count += 1
        else:
            not_upper_count += 1

    response = 'Whatever.'
    if re.search(ends_with_a_q_re, input_str):
        response = "Sure."
    if re.search(no_stmt_re, input_str) or len( input_str ) == 0:
        response = "Fine. Be that way!"
    if input_str.isupper() or upper_count > not_upper_count:
        response = "Whoa, chill out!"

    return response
