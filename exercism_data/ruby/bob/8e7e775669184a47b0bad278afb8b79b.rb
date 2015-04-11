class Bob

  attr_reader :msg

  def hey(msg)
    @msg = msg

    if silence?
      'Fine. Be that way!'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end


  private

  def silence?
    msg.strip == ''
  end

  def shouting?
    msg == msg.upcase
  end

  def question?
    msg.end_with?('?')
  end

end
