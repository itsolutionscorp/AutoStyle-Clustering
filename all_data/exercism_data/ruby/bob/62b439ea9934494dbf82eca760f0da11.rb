class Bob
  def hey(msg)
    case msg
    when /\?$/.match(msg)
      'Sure.'
    when /^[A-Z]+$/.match(msg)
      'Woah, chill out!'
    when msg.nil?
      'Fine, Be that way!'
    else
      'Whatever.'
    end
  end
end
