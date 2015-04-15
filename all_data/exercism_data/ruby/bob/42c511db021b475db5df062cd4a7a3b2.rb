class Bob
  def hey(sentence)
    return 'Fine. Be that way!' if sentence.strip.empty?
    return 'Woah, chill out!' if sentence.upcase == sentence && sentence.match(/[a-zA-Z]/)
    return 'Sure.' if sentence.end_with? '?'
    'Whatever.'
  end
end
