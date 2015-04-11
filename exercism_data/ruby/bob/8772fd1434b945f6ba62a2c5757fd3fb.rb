class Bob
  def hey(request)
    return 'Fine. Be that way!' if silence?(request)
    return 'Woah, chill out!' if shouting?(request)
    return 'Sure.' if question?(request)
    "Whatever."
  end

  private

  def silence?(sentence)
    sentence.nil? || sentence.strip.empty?
  end

  def shouting?(sentence)
    sentence[0...-1].upcase == sentence[0...-1]
  end

  def question?(sentence)
    sentence.end_with?("?")
  end

end
