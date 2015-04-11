class Bob
  def hey(msg)
    case msg
    when question?(msg)
      'Sure.'
    when shouting?(msg)
      'Woah, chill out!'
    when no_message?(msg)
      'Fine, Be that way!'
    else
      'Whatever.'
    end
  end
end

def shouting?(msg)
  /^[A-Z]+$/.match(msg)
end

def no_message?(msg)
  msg.nil?
end

def question?(msg)
  /\?$/.match(msg)
end
