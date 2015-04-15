class Bob

  def hey(sentence)
    if question?(sentence)
      answer(:question)
    elsif yelling?(sentence)
      answer(:yelling)
    elsif saying_nothing?(sentence)
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

  def question?(sentence)
    sentence.end_with?("?") and not yelling?(sentence)
  end

  def yelling?(sentence)
    sentence == sentence.upcase and sentence != sentence.swapcase
  end

  def saying_nothing?(sentence)
    sentence.strip.empty?
  end

end
