class Bob

  def hey(words)
    speech = Speech.new(words)

    case
    when speech.shouting?
      "Woah, chill out!"
    when speech.question?
      "Sure."
    when speech.silence?
      "Fine. Be that way."
    else
      "Whatever."
    end
  end

  class Speech
    attr_reader :words

    def initialize(words)
      @words = words
    end

    def shouting?
      words =~ /[A-Z]{2,}/
    end

    def question?
      words =~ /\?\z/
    end

    def silence?
      words.to_s.empty?
    end
  end
end
