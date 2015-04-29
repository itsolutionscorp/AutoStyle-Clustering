class Bob

  def initialize
    @responses = { silence:'Fine. Be that way!',
                   question:'Sure.', 
                   yell:'Woah, chill out!', 
                   unknown: 'Whatever.' }
  end

  def hey(text)
    @responses[sentiment(text)]
  end

  def sentiment(text)
    return :silence if text.strip.empty?
    return :yell if text == text.upcase and not text == text.downcase
    return :question if text.end_with? '?'
    return :unknown
  end

end
