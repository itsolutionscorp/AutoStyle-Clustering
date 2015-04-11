class Bob

  def hey(statement)
    Sentence.new(statement).respond
  end

end

class Sentence

  def initialize(statement)
    @statement = statement || ''
  end

  def respond
    if silence?
      'Fine. Be that way!'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence?
    @statement.empty?
  end

  def shouting?
    @statement.upcase == @statement
  end

  def question?
    @statement.end_with? '?'
  end

end
