class Bob
 def hey text
   statement = Statement.new(text) 
   return 'Sure' if statement.isAQuestion?
   return 'Woah, chill out!' if statement.isAShout?
   return 'Fine. Be that way!' if statement.isASilence?
   return 'Whatever.'
 end 
end

class Statement
  def initialize text
    @text = text
  end

  def isAQuestion?
    @text.include?("?")
  end

  def isAShout?
    @text.include?("!")
  end

  def isASilence?
    (0..1).include?(@text.squeeze.length)
  end
end
