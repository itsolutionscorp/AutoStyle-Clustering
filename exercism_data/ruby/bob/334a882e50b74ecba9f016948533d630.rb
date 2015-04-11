class Bob
  def hey(msg)
    case msg
    when /\A\s*\z/, nil
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
