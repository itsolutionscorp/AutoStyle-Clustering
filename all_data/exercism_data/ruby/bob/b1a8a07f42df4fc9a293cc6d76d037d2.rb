class Bob
  
  def hey(text)
    sentence = Sentence.new(text)
    
    if sentence.nothing?
      'Fine. Be that way.'
    
    elsif sentence.angry?
      'Woah, chill out!'
    
    elsif sentence.question?
      'Sure.'
      
    else
      'Whatever.'
    end
  end
end

class Sentence
  
  def initialize(txt)
    @txt = txt
  end
  
  def nothing?
    @txt.nil? || @txt.empty?
  end
  
  def angry?
    @txt == @txt.upcase
  end
  
  def question?
    @txt.end_with?('?')
  end  
end
