class Bob
  def hey(greeting)
    greeting = Greeting.new({:greeting => greeting})
    
    return greeting.response
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
    greeting.strip.empty?
  end
  
  def shouting?(greeting)
    greeting.upcase == greeting
  end
  
  def asking?(greeting)
    greeting.end_with?("?")
  end
end
