def transform(old_data):
    newdata = {}
    for score, letters in old_data.iteritems():
        _transformscore(newdata, score, letters)

    return newdata

def _transformscore(newdata, score, letters):
    for letter in letters:
        newdata[letter.lower()] = score  
