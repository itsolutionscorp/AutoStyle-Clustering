class Bob
  def hey (sentence)
    Responder::reply(sentence)
  end

  class Responder
    def self.reply(sentence)
      sentence = sentence.to_s

      case
        when yelled?(sentence)
          @response[:toYelling]
        when asked?(sentence)
          @response[:toQuestion]
        when nothingSaid?(sentence)
          @response[:toSilence]
        else
          @response[:toAnythingElse]
      end
    end

    private
    attr_reader :response

    @response = Hash[
        :toYelling      => "Woah, chill out!",
        :toQuestion     => "Sure.",
        :toSilence      => "Fine. Be that way!",
        :toAnythingElse => "Whatever."
    ]

    def self.asked?(sentence)
      sentence.end_with?('?')
    end

    def self.yelled?(sentence)
      sentence.match(/[a-zA-Z]/) != nil and sentence.upcase == sentence
    end

    def self.nothingSaid?(sentence)
      sentence.strip.empty?
    end
  end
end
