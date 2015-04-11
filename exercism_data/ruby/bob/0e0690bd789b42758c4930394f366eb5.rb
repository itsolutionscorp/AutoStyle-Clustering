class Bob

  def hey(words)
    input = Input.new(words).analyze
    Output.new(input).say 
  end

  class Output

    attr_reader :request

    def initialize(request)
      @request = request
    end

    def say
      send("respond_to_#{request}")
    end

    private

    def respond_to_question
      'Sure.'
    end

    def respond_to_silence
      'Fine. Be that way!'
    end

    def respond_to_shouting
      'Woah, chill out!'
    end

    def respond_to_other
      'Whatever.'
    end

  end

  class Input

    TYPES = [:shouting, :question, :silence, :other]

    attr_reader :words

    def initialize(words)
      @words = words
    end

    def analyze
      TYPES.detect { |type| send("#{type}?") }
    end

    private

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
      !shouting? && !question? && !silence?
    end

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
