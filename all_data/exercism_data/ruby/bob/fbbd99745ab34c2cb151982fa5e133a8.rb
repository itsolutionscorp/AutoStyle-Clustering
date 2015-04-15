class Bob

  def initialize(responses = default_responses)
    @responses = responses
  end

  def default_responses
    { silence:'Fine. Be that way!',
      question:'Sure.', 
      yell:'Woah, chill out!', 
      unknown: 'Whatever.' }
  end

  def hey(text)
    @responses[sentiment(text)]
  end

  def sentiment(text)
    return :silence if silence?(text)
    return :yell if yelling?(text)
    return :question if question?(text)
    return :unknown
  end

  def silence?(text)
    text.strip.empty?
  end

  def yelling?(text)
    text == text.upcase and not text == text.downcase
  end

  def question?(text)
    text.end_with? '?'
  end

end
