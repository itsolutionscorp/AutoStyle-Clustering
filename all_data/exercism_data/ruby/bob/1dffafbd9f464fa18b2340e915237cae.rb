class Bob

  def hey(input)
    return 'Fine. Be that way!' if input.to_s == ""
    return 'Woah, chill out!' if input.upcase == input
    return 'Sure.' if input =~ /\?\z/
    'Whatever.'
  end

end
