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

# TODO: can make it it a value object
class Statement
  def initialize statement
    @statement = statement
  end
  attr_reader :statement
  attr_reader :type

  def statement= statement
    @statement = statement
    @type = nil
  end
  
  def type
    @type ||=
      begin
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
  end

  def === expected
    self.type
  end
  
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
