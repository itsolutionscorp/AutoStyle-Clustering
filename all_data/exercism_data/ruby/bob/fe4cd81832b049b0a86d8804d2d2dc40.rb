class Bob
  def hey(input)
    return 'Fine. Be that way!' if input.delete(' ').empty?
    return 'Woah, chill out!' if input.match(/[a-z]/i) && input == input.upcase
    return 'Sure.' if input.end_with?('?')
    return 'Whatever.'
  end
end
