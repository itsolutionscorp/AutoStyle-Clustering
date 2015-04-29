class Bob
  def hey(words)
    sentence = Sentence.new(words)

    if sentence.saying_nothing?
      'Fine. Be that way!'
    elsif sentence.yelling?
      'Woah, chill out!'
    elsif sentence.asking?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Sentence
    def initialize(sentence)
      @sentence = sentence
    end

    def saying_nothing?
      @sentence.strip.empty?
    end

    def yelling?
      @sentence == @sentence.upcase
    end

    def asking?
      @sentence.end_with?('?')
    end
  end
end
