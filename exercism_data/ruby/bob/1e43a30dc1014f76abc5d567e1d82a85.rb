class Bob

  IS_EMPTY                = /\A\s*\Z/
  HAS_CAPITALS            = /[A-Z]+/
  HAS_NO_LOWER_CASE       = /\A[^a-z]*\Z/
  ENDS_WITH_QUESTION_MARK = /\?\Z/

  def hey(input)
    return 'Fine. Be that way!' if input =~ IS_EMPTY
    return 'Woah, chill out!'   if input =~ HAS_CAPITALS && input =~ HAS_NO_LOWER_CASE
    return 'Sure.'              if input =~ ENDS_WITH_QUESTION_MARK
    'Whatever.'
  end

end
