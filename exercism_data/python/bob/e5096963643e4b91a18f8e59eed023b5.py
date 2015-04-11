class Bob(object):
  
  question_response = "Sure."
  null_response = "Fine. Be that way!"
  yelling_response = "Woah, chill out!"
  default_response = "Whatever."

  def hey(self, address):
    from unicodedata import category
    def test_letter(c): return category(unicode(c)) in ['Ll', 'Lu']
    def test_upper(c): return category(unicode(c)) == 'Lu'

    address = address.strip()

    if not isinstance(address, basestring):
      return self.default_response
    elif not address:
      return self.null_response 
    elif any(map(test_letter, address)) and \
         all(map(test_upper, filter(test_letter, address))):
      return self.yelling_response
    elif address[-1] == "?":
      return self.question_response
    else:
      return self.default_response
