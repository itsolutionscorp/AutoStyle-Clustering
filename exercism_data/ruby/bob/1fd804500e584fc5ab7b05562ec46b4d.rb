class Bob
  attr_accessor :arg
  def initalize
    @arg = arg
  end
  
  def hey(arg)
    whoa(arg)
    whatever(arg)
  end
private  
  def whoa(arg)
    "Whoa, chill out!" if arg.upcase == arg
  end

  def whatever(arg)
    "Whatever." if arg.end_with?('.')
  end

end
