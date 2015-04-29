class Bob
  def hey str
    String === str or raise 'argument to Bob#hey must be a kind of String'

    return 'Fine. Be that way!' if silence?  str # whitespace
    return 'Woah, chill out!'   if yell?     str # all caps
    return 'Sure.'              if question? str # ending '?'
    return 'Whatever.'
  end
  private
  def silence? str
    # whitespace
    str.strip == ''
  end
  def yell? str
    # all caps
    str.count('A-Z') > 0 and
      str.count('a-z') == 0
  end
  def question? str
    # ending '?'
    str.end_with? '?'
  end
end
