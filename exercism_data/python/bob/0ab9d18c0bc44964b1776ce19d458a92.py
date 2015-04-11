def hey(interaction):
    if _is_silence(interaction):
        return 'Fine. Be that way!'
    elif _is_shouting(interaction):
        return 'Whoa, chill out!'
    elif _is_question(interaction):
        return 'Sure.'
    else:
        return 'Whatever.'


def _is_silence(interaction):
    return interaction.strip() == ''


def _is_shouting(interaction):
    return interaction.isupper()


def _is_question(interaction):
    return interaction.endswith('?')
