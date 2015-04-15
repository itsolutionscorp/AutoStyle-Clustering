class Bob

  def hey(words)
    @words = words.to_s.strip
    response
  end

  private

  attr_reader :words

  def response
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
    words.empty?
  end

  def shouting?
    words.upcase == words
  end

  def question?
    words.end_with? '?'
  end
end
