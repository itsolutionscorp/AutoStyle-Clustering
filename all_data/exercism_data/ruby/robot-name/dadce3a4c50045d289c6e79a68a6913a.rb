class Robot
  
  @@robot_names=[]
  attr_accessor :name
  
  def set_robot_name(old_name=nil)
    
    alpha = ('a'..'z').to_a + ('A'..'Z').to_a
  
    loop do 
      @name = alpha[Random.rand(52)] + alpha[Random.rand(52)] +
              Random.rand(10).to_s + Random.rand(10).to_s + Random.rand(10).to_s              
      if @@robot_names & [@name] == [] and old_name != @name
        @@robot_names << @name ; break
      end
    end
    
    
  end
  
  def initialize
    self.set_robot_name
  end
  
  def reset
    old_name = @@robot_names & [@name]
    self.set_robot_name(old_name)
  end
  
end
