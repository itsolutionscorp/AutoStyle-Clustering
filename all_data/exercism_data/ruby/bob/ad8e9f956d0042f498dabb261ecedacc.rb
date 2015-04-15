class Bob

  HAS_CAPITALS            = /[A-Z]+/
  HAS_NO_LOWER_CASE       = /\A[^a-z]*\Z/

  def hey(input)
    return 'Woah, chill out!'   if input =~ HAS_CAPITALS && input =~ HAS_NO_LOWER_CASE
    return 'Fine. Be that way!' if input.strip.empty?
    return 'Sure.'              if input.end_with?('?')
    'Whatever.'
  end

end
