class Teen
  @responses = {}
  def hey (greeting)
    @responses[Greeting.new(greeting).type_of]
  end
end



class Bob < Teen

  def initialize 
    @responses  = {
      none: "Fine. Be that way!", 
      yell: "Woah, chill out!", 
      question: "Sure.", 
      random: "Whatever."
    }
  end

end



class Greeting 
  def initialize (greeting)
    @greeting = greeting.strip
  end

  def type_of
    if @greeting.length == 0
      :none
    elsif @greeting == @greeting.upcase && @greeting.match(/[a-zA-Z]/)
      :yell
    elsif @greeting[-1,1] == '?'
      :question
    else
      :random
    end
        
  end
end
