class Bob

  SILENCE       = /\A\s*\Z/
  QUESTION      = /\?\Z/
  NO_LOWER_CASE = /\A[^a-z]*\Z/
  CAPITALS      = /[A-Z]+/

  def hey(input)
    return 'Fine. Be that way!' if input =~ SILENCE
    return 'Woah, chill out!'   if input =~ NO_LOWER_CASE && input =~ CAPITALS
    return 'Sure.'              if input =~ QUESTION
    'Whatever.'
  end
end
