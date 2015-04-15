class Bob
  attr_reader :msg

  def hey(msg)
    @msg = msg
    return 'Fine. Be that way!' if asking?
    return 'Woah, chill out!'   if yelling?
    return 'Sure.'              if questioning?
    'Whatever.'
  end

  private
  def asking?
    msg.to_s.empty?
  end

  def yelling?
    msg == msg.upcase
  end

  def questioning?
    msg.end_with? '?'
  end
end
