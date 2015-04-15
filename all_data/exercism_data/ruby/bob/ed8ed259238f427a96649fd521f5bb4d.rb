class Bob
  def hey(message)
    case
    when received_empty_message?(message)
      'Fine. Be that way!'
    when received_shout?(message)
      'Woah, chill out!'
    when received_question?(message)
      'Sure.'      
    else
      'Whatever.'
    end
  end

  def received_empty_message?(message)
    message.strip == ''
  end

  def received_shout?(message)
    message == message.upcase
  end

  def received_question?(message)
    message.end_with?("?")
  end
end
