class Bob
  def hey(input_message)
    @message = input_message

    if shouting? && has_letters?
      'Woah, chill out!'
    elsif question? && not_multiline?
      'Sure.'
    elsif silence? && not_multiline?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  attr_reader :message

  def shouting?
    message.upcase == message
  end

  def has_letters?
    message.match(/[a-zA-Z]/)
  end

  def question?
    message.match(/^.+\?$/)
  end

  def not_multiline?
    message.lines.length <= 1
  end

  def silence?
    message.match(/^[ \t]*$/)
  end
end
