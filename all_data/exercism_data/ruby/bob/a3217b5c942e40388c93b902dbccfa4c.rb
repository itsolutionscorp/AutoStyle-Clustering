class Bob
  def hey(input)
    return 'Fine. Be that way!' if input.strip.empty?
    return 'Woah, chill out!'   if input == input.upcase && input =~ /[A-Za-z]/
    return 'Sure.'              if input.end_with?('?')
    'Whatever.'
  end
end
