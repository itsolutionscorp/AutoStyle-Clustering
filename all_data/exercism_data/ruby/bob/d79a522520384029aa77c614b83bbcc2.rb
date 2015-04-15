class Bob
  def hey(message)
    if is_nothing(message) 
      return 'Fine. Be that way!'
    end
    
    if is_upper_case(message) 
      return 'Woah, chill out!'
    end

    if is_question(message) 
      return 'Sure.'
    end
    
    'Whatever.'
  end
end

def is_nothing(message)
  message.nil? || message.strip.empty?
end

def is_question(message)
  message[message.length - 1] == '?'
end

def is_upper_case(message)
  message.each_char do | ch |
    if ch >= 'a' and ch < 'z'
      return false
    end
  end
  
  true
end
