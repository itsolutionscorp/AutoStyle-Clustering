class Bob
  def hey(what)
    return 'Fine. Be that way!' if what.strip.empty?
    return 'Woah, chill out!' if what.upcase == what
    return 'Sure.' if what.end_with?('?')
    return 'Whatever.'
  end
end
