class Bob

  class Answer
    attr_reader :text

    def initialize @text, @matcher
    end

    def match? phrase
      @matcher.call phrase
    end
  end

  ANSWERS = {
    silence:  Answer.new('Fine. Be that way!', -> phrase { phrase.strip.empty? }),
    shout:    Answer.new('Woah, chill out!',   -> phrase { phrase.upcase == phrase }),
    question: Answer.new('Sure.',              -> phrase { phrase.end_with? '?' }),
    other:    Answer.new('Whatever.',          -> phrase { true })
  }.values.freeze

  def hey phrase
    ANSWERS.find{ |answer| answer.match? phrase.to_s }.text
  end

end
