class Bob
  
  def hey something
    speech = Speech.new(something)
    if speech.empty?
      'Fine. Be that way!'
    elsif speech.yell?
      'Woah, chill out!'
    elsif speech.question?
      'Sure.'  
    else
      'Whatever.'
    end
  end

end

class Speech
  
  def initialize(str)
    @str = str
  end

  def empty?
    @str.nil? or @str !~ /\S/
  end
  
  def yell?
    @str == @str.upcase
  end
  
  def question?
    @str.end_with? "?"
  end
  
end
