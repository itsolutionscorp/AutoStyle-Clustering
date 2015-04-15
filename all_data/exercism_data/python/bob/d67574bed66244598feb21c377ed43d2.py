#
# igor@tamarapatino.org <Igor Támara>
# Study the code and let me know of improvements


def hey(what):
    """We end the function as soon as we can, so we return and avoid
    unusable elif conditions at the end that are not evaluated.
    """
    if len(what.strip()) == 0:
        # Nothing was said
        return 'Fine. Be that way!'
    if (
            what.upper() == what and
            len([char for char in what if char.isalpha()]) > 0
    ):
        # Felt yelled, and we found letters
        return 'Whoa, chill out!'
    if what.endswith('?') > 0:
        # Answering the question
        return 'Sure.'
    # Default answer
    return 'Whatever.'
