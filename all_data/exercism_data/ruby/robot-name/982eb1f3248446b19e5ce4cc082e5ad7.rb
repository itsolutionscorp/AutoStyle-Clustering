class Robot
  def initialize
    @name = ""
    2.times{@name << (65 + rand(25)).chr}
    @name.concat(rand(9999).to_s)
  end
  
  def name
    @name
  end
  
  def reset
    @name = Robot.new.name
  end
  
  
end
