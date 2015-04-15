class Bob
  def hey(msg)
    if no_message?(msg)
      'Fine. Be that way!'
    elsif shouting?(msg)
      'Woah, chill out!'
    elsif question?(msg)
      'Sure.'
    else
      'Whatever.'
    end
  end
end

def shouting?(msg)
  /^[^a-z]+$/.match(msg) && /[A-Z]/.match(msg)
end

def no_message?(msg)
  msg.nil? or msg.strip == ''
end

def question?(msg)
  /\?\Z/.match(msg)
end
