class Bob
  def hey message
    @message = message
    if is_empty?
      'Fine. Be that way!'
    elsif is_all_caps?
      'Woah, chill out!'
    elsif is_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def is_all_caps?
    lowcase = @message.scan(/\p{Lower}/).size
    upcase = @message.scan(/\p{Upper}/).size
    lowcase == 0 and upcase > 0
  end

  def is_question?
    @message[-1] == '?'
  end

  def is_empty?
    @message.strip.empty?
  end
end
