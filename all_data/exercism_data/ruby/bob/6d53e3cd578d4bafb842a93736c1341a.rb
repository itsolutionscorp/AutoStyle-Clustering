class Bob
  def hey phrase 
    return 'Fine. Be that way!' if (phrase.nil? || phrase.strip.empty?)
    return 'Woah, chill out!' if phrase == phrase.upcase
    return 'Sure.' if phrase.end_with?('?') 
    'Whatever.'
  end
end
