class Bob
  
  def hey(message)
    case message
    when shouting
      'Woah, chill out!'
    when asking_a_question
      'Sure.'
    when stating_anything
      'Whatever.'
    else
      # actually the readme tell us to put in a !, not . at the end
      'Fine. Be that way.'
    end
  end
  
  private

  def asking_a_question
    /.*\w+\?\z/
  end
  
  def shouting
    /\A[\s\d\'A-Z!\,\?%\^\*@\#\$\(]+\z/
  end
  
  def stating_anything
    /.*\w+/
  end

end
