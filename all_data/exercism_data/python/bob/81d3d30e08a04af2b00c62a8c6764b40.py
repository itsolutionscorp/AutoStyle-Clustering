"""The hey module mimics the conversational responses of surley teenager Bob"""

from unidecode import unidecode

def shouting(voice):
    """Tests if the voice string is shouting. If the string contains capital\
       letters and no lowercase letters, it is considered a shout."""
    return voice.isupper()

def asking(voice):
    """Tests if a the voice string is a question. If it is terminated by a '?'
    character it is a question."""
    return voice.endswith("?")

def silent(voice):
    """Tests if a the voice is silent by checking for an empty string."""
    return not len(voice)

def hey(voice):
    """the 'hey' function takes whatever you say to Bob, compares them against
    various conversational tests (shouting,asking,silent) in priority order
    and selects an appropriate conversational reponse (at least for Bob)."""

    response = "Whatever." # default response

    # Terminal whitespace removed (strip); Non-ASCII characters
    # translated (unidecode)
    
    filtered_voice = unidecode(voice.strip())

    if asking(filtered_voice) and not shouting(filtered_voice):
        response = "Sure."
    elif shouting(filtered_voice):
        response = "Whoa, chill out!"
    elif silent(filtered_voice):
        response = "Fine. Be that way!"

    return response
