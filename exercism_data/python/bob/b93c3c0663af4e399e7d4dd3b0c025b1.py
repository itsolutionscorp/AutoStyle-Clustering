'''
# Bob

Bob is a lackadaisical teenager. In conversation, his responses are very limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.
'''

def hey(query_text):
    
    # "Silence" response (query has no content or just whitespace content)
    if query_text == None or query_text.isspace() or len(query_text) == 0:
        return u'Fine. Be that way!'

    # Check whether caps-based characters in the query exist and are upper case
    if query_text.isupper():
        return u'Whoa, chill out!'

    # Check whether it's a question
    trimmed_query_text = query_text.strip()
    if trimmed_query_text[-1:] == u'?':
        return u'Sure.'

    # Default response
    return u'Whatever.';
