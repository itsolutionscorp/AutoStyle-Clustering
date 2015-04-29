require 'forwardable'

class Statement
  def initialize(value)
    @value = value
    @type = calculate_type
  end
  attr_reader :value, :type

  def to_s
    "Statement \"#{value}\" of type #{type.inspect}"
  end

  # is a Value object. but not needed for the tests to pass
  # and abstracting it makes code harder to read
  # def ==(other)
  #   self.class == other.class && value == other.value && type == other.type
  # end
  # alias :eql? :==

  # def hash
  #   self.class.hash ^ [value, type].hash
  # end

  private

  def calculate_type
    return :blank_stare if blank?
    return :shout if shouting?
    return :question if question?
    return :shrug
  end

  def blank?
    value.nil? || value.strip.empty?
  end

  def shouting?
    value.upcase == value
  end

  def question?
    value[-1] == '?'
  end
end

class LackadaisicalTeenagerResponse
  RESPONSES = { blank_stare: 'Fine. Be that way!',
    shout: 'Woah, chill out!',
    question: 'Sure.',
    shrug: 'Whatever.' }.freeze

  def self.response_for_type(type)
    RESPONSES[type]
  end
end

class Bob
  extend Forwardable

  def initialize(canned_responses=LackadaisicalTeenagerResponse)
    @responder = canned_responses
  end
  attr_accessor :responder
  delegate :response_for_type => :responder

  def hey(statement)
    statement = Statement.new(statement)
    response_for_type(statement.type)
  end
end
