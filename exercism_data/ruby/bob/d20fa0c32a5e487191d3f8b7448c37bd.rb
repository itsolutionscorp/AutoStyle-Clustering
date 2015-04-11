class Bob

  def hey(said)
    Conversation.new(said).response
  end

end

class Conversation

  attr_reader :response

  def initialize(said)
    @type = ConversationType.new said
    @response = Response.say @type
  end

end

class ConversationType

  def initialize(said)
    @said = said
  end

  def to_sym
    which
  end

    private

    def which
      return :whispering if is_whispering?
      return :shouting if is_shouting?
      return :question if is_question?
      :bored
    end

    def is_whispering?
      @said.to_s.strip.empty?
    end

    def is_shouting?
      @said.upcase == @said
    end

    def is_question?
      @said.end_with? '?'
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
