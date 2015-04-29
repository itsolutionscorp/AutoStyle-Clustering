class Bob

  attr_reader :words

  def hey(words)
    @words = words
    responses[get_response]
  end

  def get_response
    if silent?
      :silent
    elsif shouting?
      :shouting
    elsif question?
      :question
    else
      :other
    end
  end

  def silent?
    words == nil || words.strip.empty?
  end

  def shouting?
    words.upcase == words
  end

  def question?
    words.end_with? '?'
  end

  def responses
    {
      silent: 'Fine. Be that way!',
      shouting: 'Woah, chill out!',
      question: 'Sure.',
      other: 'Whatever.'
    }
  end

end
