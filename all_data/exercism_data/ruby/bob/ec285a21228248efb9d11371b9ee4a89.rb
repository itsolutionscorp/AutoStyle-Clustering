class Bob
  def hey(msg)
    case msg
    when msg.end_with?("?")
      'Sure.'
    when msg.upcase == msg
      'Woah, chill out!'
    when msg.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end

  end
end
