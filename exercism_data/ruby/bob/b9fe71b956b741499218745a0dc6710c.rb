class LackadaisicalTeenagerResponse
  RESPONSES = {
    blank: 'Fine. Be that way!',
    shouted: 'Woah, chill out!',
    question: 'Sure.',
    whatever: 'Whatever.'
  }

  class << self
    def for type
      RESPONSES[type]
    end
    alias :[] :for
  end
end

module LackadaisicalTeenager
  def response
    @response ||= LackadaisicalTeenagerResponse
  end
end

class Bob
  include LackadaisicalTeenager
    
  def hey statement
    type = Statement.new(statement).type
    response[type]
  end
end

# TODO: can make it it a value object
class Statement
  def initialize statement
    @statement = statement
  end
  attr_reader :statement

  def statement= statement
    @statement = statement
    @type = nil
  end
  
  def type
    @type ||=
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
