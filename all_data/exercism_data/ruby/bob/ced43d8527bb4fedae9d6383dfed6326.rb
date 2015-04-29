# possible responses: 'Whatever', 'Woah, chill out!', 'Sure.', 'Fine. Be that way!'
class Bob
  def hey(words)
    Response.new(words).respond
  end
end

class Response
  attr_accessor :words

  AGREEMENT     = 'Sure.'
  ANNOYANCE     = 'Fine. Be that way!'
  CHILL         = 'Woah, chill out!'
  INDIFFERENCE  = 'Whatever.' 

  def initialize words
    self.words = words
  end

  def respond
    return ANNOYANCE if is_silent?
    return CHILL if is_yelling?
    return AGREEMENT if is_asking?
    INDIFFERENCE 
  end

  private
  def is_asking? 
    words.end_with?('?')
  end

  def is_yelling? 
    words.upcase == words
  end

  def is_silent? 
    words.nil? || words.strip.length.zero?
  end
end
