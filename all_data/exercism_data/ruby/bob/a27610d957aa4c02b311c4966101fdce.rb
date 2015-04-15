class Bob
  attr_accessor :arg
  def initalize
    @arg = arg
  end
  
  def hey(arg)
    return "Whoa, chill out!" if whoa(arg)
    return "Whatever." if whatever(arg)
    return "Sure." if sure(arg)
    
  end
private  
  def whoa(arg)
     arg.upcase == arg
  end
  def whatever(arg)
    arg.end_with?('.') || ( arg.upcase != arg && arg.end_with?('!') )
  end
  def sure(arg)
    arg.end_with?('?')
  end

end
