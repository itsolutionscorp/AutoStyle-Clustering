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
    if silent?
      "Fine. Be that way!"
    elsif shouting?
      "Woah, chill out!"
    elsif asking?
      "Sure."
    else
      "Whatever."
    end
  end
  
  private
  
  def silent?
    greeting.strip.empty?
  end
  
  def shouting?
    greeting.upcase == greeting
  end
  
  def asking?
    greeting.end_with?("?")
  end
end
