class Robot
  
  attr_accessor :name

  
  def set_robot_name(old_name=nil)
    
    alphabets = ('a'..'z').to_a + ('A'..'Z').to_a
    
    loop do 
      self.name = alphabets.sample(2).join + Random.rand(0..1000).to_s.rjust(3,'0')              
      break if old_name != self.name        
    end
    
  end


  
  def initialize
    set_robot_name
  end


  
  def reset
    old_name = name
    set_robot_name(old_name)
  end


  
end
