class Bob

  def hey(sentence)
    Responder.new(sentence).response
  end

  class Responder

    attr_reader :sentence

    RESPONSES = [
      Response.new('Fine. Be that way!', method(:blank?)),
      Response.new('Woah, chill out!', method(:yelling?)),
      Response.new('Sure.', method(:asking?)),
      Response.new('Whatever.', method(:whatever?))
    ]

    def initialize(sentence)
      @sentence = sentence
    end

    def response
      RESPONSES.select { |response| response.condition.call(sentence) }.first[:message]
    end

  end

  class Response
    attr_reader :message, :condition

    def initialize(message, condition)
      @message = message
      @condition = condition
    end
  end

end
