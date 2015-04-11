class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence)

    if sentence.empty?
      'Fine. Be that way!'
    elsif sentence.yelling?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  class Sentence
    def initialize(sentence)
      @sentence = sentence.to_s
    end

    def empty?
      sentence.strip.empty?
    end

    def yelling?
      sentence == sentence.upcase
    end

    def question?
      sentence.end_with? '?'
    end

    private

    attr_reader :sentence
  end
end
