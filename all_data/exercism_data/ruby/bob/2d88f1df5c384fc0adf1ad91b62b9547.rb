class Bob
  attr :msg
  def hey(msg)
    @msg = msg
    case
    when empty_message?
      'Fine. Be that way!'
    when shouty_message?
      'Woah, chill out!'
    when interrogative_message?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def empty_message?
    msg.nil? || msg.empty? || msg =~ /\A\s+\z/
  end

  def shouty_message?
    msg == msg.upcase
  end

  def interrogative_message?
    msg.end_with?('?')
  end

end