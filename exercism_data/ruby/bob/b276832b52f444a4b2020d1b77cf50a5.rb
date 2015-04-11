require 'pry'
class Bob

  def hey(said)
    Conversation.new(said).response
  end

end

class Conversation

  attr_accessor :said, :response, :type

  def initialize(said)
    @said = said
    @type = ConversationType.new @said
    @response = Response.say(@type)
  end

  def response
    "#{@response}"
  end

end

class ConversationType

  attr_accessor :said

  def initialize(said)
    @said = said
  end

  def to_sym
    which.to_sym
  end

    private

    def which
      return 'whispering' if is_whispering?
      return 'shouting' if is_shouting?
      return 'question' if is_question?
      'bored'
    end

    def is_whispering?
      true if @said.nil? or @said.strip.empty?
    end

    def is_shouting?
      true if @said.upcase == @said
    end

    def is_question?
      true if @said[-1] == '?'
    end

end

class Response

  RESPONSE = {
    bored: 'Whatever.',
    shouting: 'Woah, chill out!',
    question: 'Sure.',
    whispering: 'Fine. Be that way!'
  }

  class << self

    def say(type)
      RESPONSE[type.to_sym]
    end

  end

end
