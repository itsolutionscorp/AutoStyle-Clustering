class Bob

  def hey(input)
    return 'Fine. Be that way!' if input.nil? or input.length == 0
    return 'Woah, chill out!' if input.upcase == input
    return 'Sure.' if input[input.length - 1] == '?'
    'Whatever.'
  end

end
