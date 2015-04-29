class Bob
  def hey(phrase)
    saying = Saying.new phrase
    
    if saying.silence?
      'Fine. Be that way!'   
    elsif saying.yelling?
      'Woah, chill out!'  
    elsif saying.question?
      'Sure.'
    elsif saying.statement?
      'Whatever.'   
    end
  end
  
end

class Saying
  def initialize(phrase)
    @phrase = phrase.to_s.strip
  end
  
  def question?
    @phrase.end_with? '?'
  end
  
  def silence?
    @phrase == ''
  end
  
  def statement?
    @phrase != ''
  end
  
  def yelling?
    @phrase == @phrase.upcase
  end
end
