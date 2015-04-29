class Bob
  def hey(phrase)
    response = 'Sure.' if phrase.end_with?('?')
    response = 'Woah, chill out!' if phrase.upcase == phrase && (/[a-zA-Z]/).match(phrase)
    response = 'Fine. Be that way!' if phrase.split.length == 0
    response = 'Whatever.' if response == nil
    return response
  end
end
