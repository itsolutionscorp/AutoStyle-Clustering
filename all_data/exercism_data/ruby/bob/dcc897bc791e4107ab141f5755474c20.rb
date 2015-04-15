class String

  def is_question
    self.index('?') == self.length - 1
  end
  
  def ends_with_exclamation_mark
      self.index('!') == self.length-1
  end
  

  #  and not self.is_question 
  def is_strong_statement
    self.index('!') != nil and self.is_uppercase
  end

  def is_uppercase
    b = self.clone
    b.upcase! == nil
  end
  
  def is_statement
    not self.is_question
  end
  
  def contains_whitesapces_only
    self.split(' ').count == 0
  end
end

class Bob
  def hey(saying)

    if saying.contains_whitesapces_only || saying.length == 0
      return 'Fine. Be that way!'
    end

    if saying.is_strong_statement || saying.is_uppercase
      return 'Woah, chill out!'
    end
    
    if saying.is_question
      return 'Sure.'
    end
    
    if saying.is_statement
      return 'Whatever.'
    end

  end
end
