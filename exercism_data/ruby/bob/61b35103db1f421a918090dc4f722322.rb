class Bob
  
  def hey(text)
    phrase = Phrase.new(text)
    
    if phrase.nothing?
      'Fine. Be that way.'
    elsif phrase.angry?
      'Woah, chill out!'
    elsif phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
    
  end
end

class Phrase
  
  def initialize(text)
    @text = text.to_s
  end
  
  def nothing?
    @text.empty?
  end
  
  def angry?
    @text == @text.upcase
  end
  
  def question?
    @text.end_with?('?')
  end  
end
