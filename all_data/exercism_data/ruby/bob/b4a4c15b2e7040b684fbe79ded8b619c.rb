class Conversation
  def initialize(gambit)
    @gambit = gambit || ''
  end
  
  def silence?()
    @gambit.strip.empty?
  end

  def shouting?()
    !silence?() && @gambit == @gambit.upcase
  end

  def question?()
    @gambit.end_with? "?"
  end
end

class Bob
  
  def hey(input)
    c = Conversation.new(input)
    c.silence?  ? "Fine. Be that way!" :
    c.shouting? ? "Woah, chill out!" :
    c.question? ? "Sure." :
    "Whatever."
  end
    
end
    
    
