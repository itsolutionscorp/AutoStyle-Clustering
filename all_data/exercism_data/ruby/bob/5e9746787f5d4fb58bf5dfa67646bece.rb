class Bob

  def hey(input)
    return 'Fine, be that way.' if input == ''
    return 'Woah, chill out!' if input == input.upcase
    return 'Sure.' if input.end_with?('?')
    return 'Whatever.'
  end
end
