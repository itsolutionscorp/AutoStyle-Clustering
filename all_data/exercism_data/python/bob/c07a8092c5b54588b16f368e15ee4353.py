''' Based on the statement, we determine it's type through string analysis
    and return the correct response'''
def return_response(statement):
    end_characters = ["?","!","."]
    if not statement or statement.isspace():
        return "Fine. Be that way!"
    all_upper = False
    for word in statement.split(" "):
        #removes the last character if applicable as we dont want that interfering with the isalpha check
        if word[-1:] in end_characters:
            word = word[:-1]
        if word.isalpha():
            if word.isupper():
                all_upper = True
            else:
                all_upper = False
                break
    if all_upper:
        return "Whoa, chill out!"
    if statement[-1:] == "?":
        return "Sure."   
    return "Whatever."

def hey(statement):
    return return_response(statement)
