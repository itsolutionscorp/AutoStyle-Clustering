class Phrase

  def initialize message
    @message = message
  end

  def silence?
    @message.strip.empty?
  end

  def questioning? 
    @message.end_with?('?')
  end
  
  def shouting?
    has_letters? and all_uppercase?
  end

  private 
  def has_letters?
    /[a-zA-Z]+/ =~ @message
  end

  def all_uppercase?
    @message.upcase == @message
  end

end

class Bob

  def hey message
    phrase = Phrase.new message

    if phrase.silence?
      'Fine. Be that way!'
    elsif phrase.shouting? 
      'Woah, chill out!'
    elsif phrase.questioning? 
      'Sure.'
    else
      'Whatever.'
    end
  end

end
