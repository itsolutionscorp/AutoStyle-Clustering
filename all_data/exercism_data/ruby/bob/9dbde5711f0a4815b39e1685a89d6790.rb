class Bob
  def hey(sentence)
    if shouted?(sentence)
      'Woah, chill out!'
    elsif question?(sentence)
      'Sure.'
    elsif silence?(sentence)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
  
  private
    
    def question?(sentence)
      sentence.end_with?("?")
    end
    
    def shouted?(sentence)
      sentence =~ /[A-Z]/ and sentence == sentence.upcase
    end
    
    def silence?(sentence)
      sentence =~ /\A\s*\Z/
    end
  
end
