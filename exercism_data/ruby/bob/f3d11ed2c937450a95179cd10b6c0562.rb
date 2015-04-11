class Bob

  def hey(message)
    @message = message
    if is_empty?
      "Fine. Be that way!" 
    elsif is_yelling? && !is_only_numeric?
      "Woah, chill out!" 
    elsif is_a_question?
      "Sure." 
    else 
      "Whatever." 
    end
  end

  private
  def is_empty?
    @message.strip.length == 0
  end

  def is_yelling?
    @message == @message.upcase
  end

  def is_a_question?
    @message.split('').last == '?'
  end

  def is_only_numeric?
    !@message.match(/[A-Za-z]/) && @message.match(/[0-9]/)
  end
end
