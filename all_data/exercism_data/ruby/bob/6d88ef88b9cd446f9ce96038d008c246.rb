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
    @text[@text.size - 1] == "?" 
  end

  def yell?
    @text.match(/[a-z]+/).nil? && !@text.match(/[A-Z]+/).nil?
  end

  def silence?
   @text.strip.empty? 
  end
end
