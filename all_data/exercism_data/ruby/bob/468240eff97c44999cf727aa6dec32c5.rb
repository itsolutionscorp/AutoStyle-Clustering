class Bob
  def hey(phrase)
    return 'Fine. Be that way!' if phrase.strip.length == 0
    return 'Whoa, chill out!' if phrase.upcase == phrase if phrase.split('').map { |x| ('A'..'Z').to_a.include? x }.flatten.uniq.include? true
    return 'Sure.' if phrase[-1] == '?'
    'Whatever.'
  end
end
