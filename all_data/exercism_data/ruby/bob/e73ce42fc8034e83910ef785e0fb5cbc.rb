class Bob

  def hey(message)
    message_type(message).respond
  end

  def message_type(message)
    MessageTypes.new(message).create
  end

end

class MessageTypes
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def message_type
    message_types.find do |message_type|
      message_type.match? message
    end
  end

  def create
    message_type.new
  end

  def message_types
    self.class.message_types
  end

  class << self
    def register(message_type)
      message_types << message_type
    end

    def message_types
      @message_types ||= []
    end
  end

end

module Respondable
  def self.included(base)
    MessageTypes.register(base)
  end

  def respond
  end
end

module Matchable
  def self.included(base)
    base.extend ClassMethods
  end

  def matcher
    false
  end

  module ClassMethods
    def match?(message)
      matcher { message }
    end
  end
end

class Silence
  include Respondable
  include Matchable

  def self.matcher
    yield.strip == ""
  end

  def respond
    "Fine. Be that way!"
  end
end

class Shouting
  include Respondable
  include Matchable

  def self.matcher
    message = yield
    message.upcase == message
  end

  def respond
    "Woah, chill out!"
  end
end

class Question
  include Respondable
  include Matchable

  def self.matcher
    yield =~ /\?$/
  end

  def respond
    "Sure."
  end
end

class Whatever
  include Respondable
  include Matchable

  def self.matcher
    true
  end

  def respond
    "Whatever."
  end
end
