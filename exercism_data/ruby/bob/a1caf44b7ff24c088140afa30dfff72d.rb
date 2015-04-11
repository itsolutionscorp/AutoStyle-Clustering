class Bob
  def initialize
    
  end

  def hey(str)
    return "Fine. Be that way!" if silent?(str) 
    return "Woah, chill out!" if shouting?(str) 
    return "Sure." if asking?(str) 
    "Whatever."
  end

  private

  def silent?(sentence)
    sentence.strip.empty?
  end

  def shouting?(sentence)
    sentence =~ /[A-Z]/ && sentence !~ /[a-z]/
  end

  def asking?(sentence)
    sentence.end_with?("?")
  end

end
