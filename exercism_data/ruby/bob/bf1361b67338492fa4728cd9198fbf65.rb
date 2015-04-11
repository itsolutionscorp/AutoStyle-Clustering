class Bob
  def hey(msg)
    case msg
    when '', nil
      'Fine. Be that way!'
    when msg.upcase
      'Woah, chill out!'
    when /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
