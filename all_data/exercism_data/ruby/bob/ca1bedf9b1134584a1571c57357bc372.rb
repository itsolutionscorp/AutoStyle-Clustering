class Word
  attr_reader :message
  def initialize(message)
    @message = message
  end
  
  def silence?
    message.strip == ""
  end
  
  def shouting?
    message == message.upcase
  end

  def question?
    message.end_with???
  end
end

class Bob
  def initialize(response)
  end
  def hey(message)
    word = Word.new message
    
    if word.silence?
      response[:silent]
    elsif word.shouting?
      response[:shouting]
    elsif word.question?
      response[:question]
    else
      response[:default]
    end
  end
  
  private
  
  def response
    {
      silent: 'Fine. Be that way!',
      shouting: "Woah, chill out!",
      question: 'Sure.',
      default: "Whatever."
    }
  end
end
