class Bob
  def hey(str)
    return 'Fine. Be that way!' if str.strip.empty?
    return 'Woah, chill out!' if str == str.upcase
    return 'Sure.' if str.end_with? '?'
    'Whatever.'
  end
end
