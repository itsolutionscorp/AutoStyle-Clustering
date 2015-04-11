class Bob
  ANSWERS = { 
    :question => 'Sure.', :something => 'Whatever.', 
    :yell => 'Woah, chill out!', :nothing => 'Fine. Be that way!' 
  }
  
  def hey(saying)
    ANSWERS[Saying.new(saying).kind]
  end
  
end

class Saying
  def initialize(phrase)
    @phrase = phrase
  end
  
  def kind
    return :nothing if @phrase.to_s.strip == ''
    if @phrase == @phrase.upcase
      :yell
    elsif @phrase[-1] == '?'
      :question
    else @phrase
      :something
    end
  end
    
end
