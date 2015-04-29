require 'singleton'

module Response
  def for type
    instance.responses[type]
  end
  alias :[] :for
end

class LackadaisicalTeenagerResponse
  include Singleton
  extend Response
  
  def initialize
    @responses = {
      blank: 'Fine. Be that way!',
      shouted: 'Woah, chill out!',
      question: 'Sure.',
      whatever: 'Whatever.'
    }.freeze
  end
  attr_reader :responses
end

module LackadaisicalTeenager
  def response
    @response = LackadaisicalTeenagerResponse
  end
end

class Bob
  include LackadaisicalTeenager
  
  def hey statement
    type = Statement.new(statement).type
    response.for type
  end
end

class Statement
  def initialize statement
    @statement = statement
    @statement.freeze
  end
  attr_reader :statement
  
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

  # value object
  def == other
    eql?(other)
  end
  
  def eql? other
    other.is_a?(self.class) && statement == other.statement
  end
  
  private
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
