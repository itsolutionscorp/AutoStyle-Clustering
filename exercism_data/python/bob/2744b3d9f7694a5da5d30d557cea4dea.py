def main():
    print "Bob module main"

def hey(input=""):
    """Bob answers 'Sure.' if you ask him a question.
    
    He answers 'Whoa, chill out!' if you yell at him.
    
    He says 'Fine. Be that way!' if you address him without actually saying
    anything.
    
    He answers 'Whatever.' to anything else.
    """
    responses = [u'Sure.', u'Whoa, chill out!', u'Fine. Be that way!', u'Whatever.']
    response = responses[3]
    input = input.strip()
    if input and input[-1] == '?':
        response = responses[0]
    if len(input) == 0:
        response = responses[2]
    if input.isupper():
        response = responses[1]
    return response

if __name__ == '__main__':
    main()
