class Bob

  class Sentence
    def initialize(sentence)
      @sentence = sentence
    end

    def question?
      @sentence.end_with?("?") and not yelling?
    end

    def yelling?
      @sentence == @sentence.upcase and @sentence != @sentence.swapcase
    end

    def saying_nothing?
      @sentence.strip.empty?
    end
  end

  def hey(sentence)
    sentence = Sentence.new(sentence)
    if sentence.question?
      answer(:question)
    elsif sentence.yelling?
      answer(:yelling)
    elsif sentence.saying_nothing?
      answer(:saying_nothing)
    else
      answer(:default)
    end
  end

  private
  def answer(behavior)
    {
      :question => 'Sure.',
      :yelling => 'Woah, chill out!',
      :saying_nothing => 'Fine. Be that way!',
      :default => 'Whatever.'
    }[behavior]
  end

end
