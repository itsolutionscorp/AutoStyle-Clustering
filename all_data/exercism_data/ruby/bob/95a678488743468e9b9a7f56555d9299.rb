class Bob

  ALL_CAPS = /\A[^a-z]+\Z/
  QUESTION = /.*\?\Z/
  EMPTY_STRING = /\A\s*\Z/

  RESPONSES = {
    question: 'Sure.',
    silence: 'Fine. Be that way!',
    shouting: 'Woah, chill out!',
    default: 'Whatever.'    
  }

  def hey(message)
    
    case message
    when EMPTY_STRING; 
      RESPONSES[:silence]
    when ALL_CAPS
      RESPONSES[:shouting]
    when QUESTION
      RESPONSES[:question]
    else
      RESPONSES[:default]
    end    
  end    
end
