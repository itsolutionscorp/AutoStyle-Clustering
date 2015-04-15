class Bob

  RESPONSES = {
    other: 'Whatever.',
    question: 'Sure.',
    silence: 'Fine. Be that way!',
    shouting: 'Woah, chill out!'
  }

  def hey(words)
    type = Analyzer.new(words).detect
    RESPONSES[type]
  end

  class Analyzer

    TYPES = [:shouting, :question, :silence, :other]

    attr_reader :words

    def initialize(words)
      @words = words
    end

    def detect
      TYPES.detect { |type| send("#{type}?") }
    end

    protected

    def shouting?
      contains_letters? && all_upper_case_letters?
    end

    def silence?
      words.strip.empty?
    end

    def question?
      words.chars.last == '?'
    end

    def other?
      !shouting && !question && !silence?
    end

    private

    def all_upper_case_letters?
      letters.all? { |c| ('A'..'Z').cover? c }
    end

    def letters
      words.chars.select { |c| ('a'..'z').cover? c.downcase }
    end

    def contains_letters?
      letters.size > 0
    end

  end

end
