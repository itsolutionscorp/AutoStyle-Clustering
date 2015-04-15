class Bob
  def hey message
    if silent message
      return 'Fine. Be that way!'
    elsif scream message
        return 'Woah, chill out!'
    elsif question message
        return 'Sure.'
    else
      return 'Whatever.'
    end
  end

  def silent message
    message.strip.empty?
  end

  def scream message
    message.upcase == message
  end

  def question message
    message[-1] == '?'
  end
end
