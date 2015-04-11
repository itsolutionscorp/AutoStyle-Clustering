class Bob

  def hey(input)
    return 'Woah, chill out!' if input.upcase == input && input =~ /[A-Z]+/
    return 'Sure.' if input.end_with?('?')
    return 'Fine. Be that way!' if input.strip.empty?
    'Whatever.'
  end

end
