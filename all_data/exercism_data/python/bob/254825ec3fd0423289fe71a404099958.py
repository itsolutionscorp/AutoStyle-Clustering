
def hey(bob_call):
        bob_call.expandtabs()
        if not bob_call or bob_call.isspace():
                bob_response = "Fine. Be that way!"

        elif (bob_call.isupper()):
                bob_response = "Whoa, chill out!"

        elif (bob_call.endswith("?")):
                bob_response = "Sure."



        else:
                bob_response = "Whatever."

        return bob_response

      
