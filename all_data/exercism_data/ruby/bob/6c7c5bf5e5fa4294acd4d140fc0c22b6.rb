class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence)
    if sentence.is_saying_nothing?
      'Fine. Be that way!'
    elsif sentence.is_yell?
      'Woah, chill out!'
    elsif sentence.is_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  class Sentence

    def initialize(string)
      @string = string
    end

    def is_question?
      string.end_with?('?')
    end

    def is_yell?
      string.upcase == string
    end

    def is_saying_nothing?
      string.strip.empty?
    end

    private

    attr_reader :string
  end
end
