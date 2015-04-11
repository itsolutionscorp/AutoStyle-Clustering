LackadaisicalTeenagerResponse = {
  blank: 'Fine. Be that way!',
  shouted: 'Woah, chill out!',
  question: 'Sure.',
  whatever: 'Whatever.'
}.freeze

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

module ValueObject
  # value object
  def == other
    eql?(other)
  end
  
  def eql? other
    other.is_a?(self.class) && value == other.value
  end
end

class Statement
  include ValueObject
  
  def initialize statement
    @value = statement
    @value.freeze
  end
  attr_reader :value
  
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
  
  private
  def blank?
    return true if value.nil?
    value.strip.empty?
  end

  def question?
    '?' == value[-1]
  end

  def shouting?
    value.upcase == value
  end  
end
