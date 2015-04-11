class Bob

  def initialize
    @answer = {
      :question => 'Sure.',
      :yelling => 'Woah, chill out!',
      :saying_nothing => 'Fine. Be that way!',
      :default => 'Whatever.'
    }
  end

  def hey(sentence)
    if is_question?(sentence)
      @answer[:question]
    elsif is_yelling?(sentence)
      @answer[:yelling]
    elsif is_saying_nothing?(sentence)
      @answer[:saying_nothing]
    else
      @answer[:default]
    end
  end

  private
  def is_question?(sentence)
    sentence.split('').last == "?" and not is_yelling?(sentence)
  end

  def is_yelling?(sentence)
    sentence == sentence.upcase and sentence != sentence.swapcase
  end

  def is_saying_nothing?(sentence)
    sentence.strip == ''
  end

end
