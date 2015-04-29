class Bob
  attr :msg
  def hey(msg)
    @msg = msg
    case
    when empty_message?
      'Fine. Be that way!'
    when msg == msg.upcase
      'Woah, chill out!'
    when msg.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

  def empty_message?
    msg.nil? || msg.empty? || msg =~ /\A\s+\z/
  end
end
