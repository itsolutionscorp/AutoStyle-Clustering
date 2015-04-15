class Bob
  def hey(input)
    input.delete! ' 0-9'
    return 'Fine. Be that way!' if input.empty?
    return 'Woah, chill out!'   if input.match(/[A-Z]/) && input.upcase == input
    return 'Sure.'              if input.end_with? '?'
    return 'Whatever.'
  end
end
