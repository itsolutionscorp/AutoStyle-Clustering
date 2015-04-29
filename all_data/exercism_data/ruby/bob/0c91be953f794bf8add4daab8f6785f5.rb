class Bob
  def hey(message, sentiment = Sentiment.new(message))
    return "Fine. Be that way!" if sentiment.uncomfortable?
    return "Woah, chill out!"   if sentiment.aggressive?
    return "Sure."              if sentiment.inquiry?
    return "Whatever."
  end

  class Sentiment
    attr_reader :phrase
    private :phrase

    def initialize(phrase)
      @phrase = phrase
    end

    def uncomfortable?
      phrase.strip.empty?
    end

    def aggressive?
      phrase !~ /[a-z]+/ && phrase =~ /[A-Z]+/
    end

    def inquiry?
      phrase.end_with?("?")
    end
  end
end
