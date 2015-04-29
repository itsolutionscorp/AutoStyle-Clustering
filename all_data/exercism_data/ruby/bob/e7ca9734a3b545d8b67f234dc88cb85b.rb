class Bob
  attr_reader :greeting
  
  def hey(greeting)
    @greeting = Greeting.new({:greeting => greeting})
    
    return @greeting.response
  end
end

class Greeting
  attr_reader :greeting
  
  def initialize(opts)
    @greeting = opts[:greeting]
  end
  
  def response
    if silent?(greeting)
      "Fine. Be that way!"
    elsif shouting?(greeting)
      "Woah, chill out!"
    elsif asking?(greeting)
      "Sure."
    else
      "Whatever."
    end
  end
  
  private
  
  def silent?(greeting)
    (greeting.empty? || greeting.squeeze == " ") ? true : false
  end
  
  def shouting?(greeting)
    greeting.upcase == greeting ? true : false 
  end
  
  def asking?(greeting)
    greeting.end_with?("?") ? true : false
  end
end
