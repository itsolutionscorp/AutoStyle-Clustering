class Bob
  
  QUESTION_REGEX = /[?]\Z/
    
  def no_message?(msg)
    msg.nil? || msg.empty?
  end
  
  def all_upper?(msg)
    msg == msg.upcase 
  end
  
  def ends_with_question?(msg)
    QUESTION_REGEX.match(msg) 
  end
  
  def hey(msg)
    if no_message?(msg)
      'Fine. Be that way!'
    elsif all_upper?(msg)
      "Woah, chill out!" 
    elsif ends_with_question?(msg)
      "Sure."
    else 
      'Whatever.'
    end
  end
  
end
