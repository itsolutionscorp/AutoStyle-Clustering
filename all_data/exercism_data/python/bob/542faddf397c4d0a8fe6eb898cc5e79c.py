
def hey(bob_call):
        if not bob_call or bob_call.isspace():
                return "Fine. Be that way!"
        elif bob_call.isupper():
                return "Whoa, chill out!"

        elif bob_call.endswith("?"):
                return "Sure."
        else:
                return "Whatever."

      
