class Bob

  def hey(sentence)
    Responder.new(sentence).response
  end

  class Responder

    attr_reader :sentence

    RESPONSES = [ {
        message: 'Fine. Be that way!',
        condition: lambda { |sentence| sentence.strip.empty? }
      }, {
        message: 'Woah, chill out!',
        condition: lambda { |sentence| sentence.upcase == sentence }
      }, {
        message: 'Sure.',
        condition: lambda { |sentence| sentence.end_with? '?' }
      }, {
        message: 'Whatever.',
        condition: lambda { |sentence| true }
      }
    ]

    def initialize(sentence)
      @sentence = sentence
    end

    def response
      RESPONSES.select { |response| response[:condition].call(sentence) }.first[:message]
    end

  end

end
