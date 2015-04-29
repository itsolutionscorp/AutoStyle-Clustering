class Bob

  RESPONSES = {
    other: 'Whatever.',
    question: 'Sure.',
    silence: 'Fine. Be that way!',
    shouting: 'Woah, chill out!'
  }

  def hey(phrase)
    chosen_response = Input.new(phrase).analyze
    RESPONSES[chosen_response]
  end

  class Input

    attr_reader :phrase

    def initialize(phrase)
      @phrase = phrase.strip
    end

    def analyze
      RESPONSES.keys.detect { |type| send("#{type}?") }
    end

    private

    def shouting?
      contains_letters? && all_upper_case_letters?
    end

    def silence?
      phrase.empty?
    end

    def question?
      phrase.end_with?('?') && !shouting?
    end

    def other?
      !shouting? && !question? && !silence?
    end

    def all_upper_case_letters?
      letters.all? { |c| ('A'..'Z').cover? c }
    end

    def letters
      phrase.chars.select { |c| ('a'..'z').cover? c.downcase }
    end

    def contains_letters?
      letters.size > 0
    end

  end

end
