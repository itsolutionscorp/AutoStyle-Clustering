def rhyme():
    this = "This is "
    rhymes = ['the horse and the hound and the horn\nthat belonged to ',
              'the farmer sowing his corn\nthat kept ',
              'the rooster that crowed in the morn\nthat woke ',
              'the priest all shaven and shorn\nthat married ',
              'the man all tattered and torn\nthat kissed ',
              'the maiden all forlorn\nthat milked ',
              'the cow with the crumpled horn\nthat tossed ',
              'the dog\nthat worried ',
              'the cat\nthat killed ',
              'the rat\nthat ate ',
              'the malt\nthat lay in ',
              'the house that Jack built.']
    rhymes.reverse()

    output = ""

    for i in range(1, len(rhymes) + 1):
        strophe = this + ''.join(reversed(rhymes[:i])) + "\n\n"
        output += strophe

    return output.strip()


if __name__ == '__main__':
    print(rhyme())
