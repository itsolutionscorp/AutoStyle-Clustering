class Bob
 def hey text
   statement = Statement.new(text) 
   return 'Fine. Be that way!' if statement.silence?
   return "Woah, chill out!" if statement.yell?
   return "Sure." if statement.question?
   'Whatever.'
 end 
end

class Statement

  def initialize text
    @text = text
  end

  def question?
    @text.end_with "?" 
  end

  def yell?
    !@text.match(/[a-z]+/) && @text.match(/[A-Z]+/)
  end

  def silence?
   @text.strip.empty? 
  end
end
