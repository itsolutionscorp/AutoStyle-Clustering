class Bob

  def hey(greet)
    return 'Fine. Be that way!' if greet.strip.empty?
    return 'Woah, chill out!' if greet.upcase == greet
    return 'Sure.' if greet.end_with? '?'
    'Whatever.'
  end

end
