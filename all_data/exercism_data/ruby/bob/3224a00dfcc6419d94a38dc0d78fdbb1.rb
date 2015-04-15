class Bob

  def initialize
    @sentence = ''
  end

  def hey(sentence)
    @sentence = sentence
    if question?
      answer(:question)
    elsif yelling?
      answer(:yelling)
    elsif saying_nothing?
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
