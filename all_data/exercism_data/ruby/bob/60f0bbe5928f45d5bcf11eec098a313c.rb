class Bob
  def hey message
    @message = message.strip

    if @message.empty?
      'Fine. Be that way!'
    elsif contains_no_lowercase_letters?
      'Woah, chill out!'
    elsif ends_with_a_question_mark?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def contains_no_lowercase_letters?
    @message.upcase == @message
  end

  def ends_with_a_question_mark?
    @message.end_with? '?'
  end
end
