class Robot
  
  @@robot_names=[]
  attr_accessor :name
  
  def set_robot_name(old_name=nil)
    
    alphabets = ('a'..'z').to_a + ('A'..'Z').to_a
    
    loop do 
      @name = (alphabets.sample(1) + alphabets.sample(1)).join +
              Random.rand(10).to_s + Random.rand(10).to_s + Random.rand(10).to_s
              
      if old_name != @name
        @@robot_names << @name ; break
      end
    end
    
    
  end
  
  def initialize
    set_robot_name
  end
  
  def reset
    old_name = @@robot_names.delete(@name)
    set_robot_name(old_name)
  end
  
end

r = Robot.new
