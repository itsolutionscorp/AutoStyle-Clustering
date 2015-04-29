class Bob

  attr_reader :words

  def hey(words)
    @words = words
    get_response
  end

  private

  def get_response
    case
    when silent?
      'Fine. Be that way!'
    when shouting?
      'Woah, chill out!'
    when question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silent?
    words.to_s.strip.empty?
  end

  def shouting?
    words.upcase == words
  end

  def question?
    words.end_with? '?'
  end
end
