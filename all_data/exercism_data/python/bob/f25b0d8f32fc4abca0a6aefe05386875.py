#!/usr/bin/env python
# Bob is a lackadaisical teenager. In conversation, his responses are very limited.
# Bob answers 'Sure.' if you ask him a question.
# He answers 'Whoa, chill out!' if you yell at him.
# He says 'Fine. Be that way!' if you address him without actually saying anything.
# He answers 'Whatever.' to anything else.

class Bob():

    def hey(self,sentence):
        # first check cases where sentence only contains numbers and punctuation
        withoutPunc = ''.join(c for c in sentence if c not in ('!','.',',','?',' '))
        if withoutPunc.isdigit():
            if sentence.endswith("?"):
                return "Sure."
            else:
                return "Whatever."
        # next check the rest of the cases, starting with empty or space only sentence
        elif sentence.isspace() or sentence == '':
           return "Fine. Be that way!"
        elif sentence == sentence.upper():
           return "Whoa, chill out!"
        elif sentence.endswith("?"):
           return "Sure."
        else:
           return "Whatever."
