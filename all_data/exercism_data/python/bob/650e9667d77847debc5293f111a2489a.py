# http://exercism.io/submissions/b9636978b9cead198438c55c

# Bob is a lackadaisical teenager. In conversation, his responses are very limited.
# Bob answers 'Sure.' if you ask him a question.
# He answers 'Whoa, chill out!' if you yell at him.
# He says 'Fine. Be that way!' if you address him without actually saying
# anything.
# He answers 'Whatever.' to anything else.

def hey(input):
    input = input.strip()
    if not input:
        return "Fine. Be that way!"
    if input.isupper():
        return "Whoa, chill out!"
    if input[-1] == '?':
        return "Sure."
    else:
        return "Whatever."
        
        
