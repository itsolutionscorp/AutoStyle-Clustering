class Bob
  def hey(msg)
    @msg = msg
    if empty?
      'Fine. Be that way!'
    elsif all_caps?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def empty?
    @msg.nil? || @msg.empty?
  end

  def all_caps?
    @msg.upcase == @msg
  end

  def question?
    @msg.end_with? '?'
  end
end
