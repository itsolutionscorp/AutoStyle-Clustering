class Bob
  def initialize
    @responses = {
      blank: 'Fine. Be that way!',
      shouted: 'Woah, chill out!',
      question: 'Sure.',
      whatever: 'Whatever.'
    }
  end
  attr_accessor :responses
    
  def hey statement
    type = Statement.new(statement).type
    responses[type]
  end
end

class Statement
  def initialize statement
    @statement = statement
  end
  attr_accessor :statement

  def type
    if blank?
      :blank
    elsif shouting?
      :shouted
    elsif question?
      :question
    else
      :whatever
    end
  end
  alias :type, :===

  protected
  def blank?
    return true if statement.nil?
    statement.strip.empty?
  end

  def question?
    '?' == statement[-1]
  end

  def shouting?
    statement.upcase == statement
  end  
end
