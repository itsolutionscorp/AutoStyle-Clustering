class Bob
  def hey(sentence)
    sentiment = Sentiment.new(sentence)

    if sentiment.silent?
      "Fine. Be that way!"
    elsif sentiment.shouting?
      "Woah, chill out!"
    elsif sentiment.question?
      "Sure."
    else
      "Whatever."
    end
  end

  class Sentiment
    attr_accessor :sentence

    def initialize(sentence)
      self.sentence = sentence
    end

    def shouting?
      sentence =~ /[A-Z]/ && sentence !~ /[a-z]/
    end

    def silent?
      sentence =~ /\A\s*\z/
    end

    def question?
      sentence =~ /\?\z/
    end
  end
end
