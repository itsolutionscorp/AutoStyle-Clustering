def hey(what):

    #Is yelling
    if what.isupper():
      return 'Whoa, chill out!'

    #Is quiet
    if what.isspace() or what == '':
      return 'Fine. Be that way!'

    #Is asking a question
    if what.endswith('?'):
      return 'Sure.'

    #Otherwise
    return "Whatever."
