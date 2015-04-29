class Bob
  def hey(x)
    return 'Woah, chill out!' if x.upcase == x and x.downcase != x
    return 'Sure.' if x.end_with? '?'
    return 'Fine. Be that way!' if x.strip.empty?
    return 'Whatever.'
  end
end
