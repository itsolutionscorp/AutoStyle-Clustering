def hey(question):
    question = question.strip()
    if not question or question.isspace() == "":
        return "Fine. Be that way!"
    if question.isupper():
        return "Whoa, chill out!"

    if question.endswith('?'):
        return "Sure."
    else:
        return "Whatever."

    
if __name__ == '__main__':
    val = hey("Let's go make out behind the gym!")
    print(val)
##    def hey( self, question):
##        self.question = question
##        if question[-1] == "!":
##            #print("Whoa, chill out!")
##            return "Whoa, chill out!"
##        if question[-1] == "?":
##            #print("Sure.")
##            return "Sure."
##        if question[-1] == ".":
##            #print("Whatever.")
##            return "Whatever."
