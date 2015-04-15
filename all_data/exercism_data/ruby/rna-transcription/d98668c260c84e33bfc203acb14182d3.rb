class Bob
  def hey(something)
    return 'Fine. Be that way.' if String(something).empty?
    return 'Sure.' if something.end_with?('?')
    return 'Woah, chill out!' if something.upcase == something
    'Whatever.'
  end
end
