class Robot
  
  def reset
    l = [*'A'..'Z']
    n = [*'0'..'9']
    @name = l.sample + l.sample + n.sample + n.sample + n.sample
  end

  def initialize
    @name = ''
    reset
  end

  def name
    @name
  end

end
