#
# by LattMongoria 2014/10/02
#
def hey(what):
    
    import unicodedata
    import string
    text = unicode(what)

    #I found the following 2 line on the internet
    #They somehow strip diacritics from unicode strings
    #I don't actually understand how they work
    text = unicodedata.normalize("NFKD", text)
    stripped_text = u"".join([ch for ch in text if not unicodedata.combining(ch)])

    #Getting rid of whitespace
    stripped_text = ''.join(stripped_text.split())
    
    #if you address him without actually saying anything.          
    if stripped_text == '':
            return 'Fine. Be that way!'

        
    #He answers 'Whoa, chill out!' if you yell at him. -- ALLCAPS

    #Making sure letters are present                
    letter_count = 0
    for c in stripped_text:
            if c in string.ascii_letters:
                    letter_count += 1

    #Checking for lowercase letters
    if letter_count > 0:                       
        tone_of_voice = 'ALLCAPS'
        for check_case in stripped_text:
                if check_case.islower():
                        tone_of_voice = 'calm'

        #If there are letters and none are lowercase that means shouting
        if (tone_of_voice == 'ALLCAPS' and letter_count > 0):
                return 'Whoa, chill out!'
                            
    #If the last character is a question mark that's a question?
    if stripped_text[-1]== '?':
            return 'Sure.'
        
    #Anything else
    else:
            return 'Whatever.'
	
