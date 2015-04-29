# Bob

class Bob
  def hey(sentence)
    return 'Fine. Be that way.' if sentence.nil? or sentence.empty?
    return 'Sure.' if sentence =~ /\?$/
    return 'Woah, chill out!' if sentence == sentence.upcase
    'Whatever.'
  end
end
