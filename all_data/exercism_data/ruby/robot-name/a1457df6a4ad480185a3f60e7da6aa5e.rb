class Robot
  def initialize
  end
  
  def name  
    letters = ('A'..'Z').to_a + ('a'..'z').to_a
    numbers = ('0'..'9').to_a
    @robot_name = letters.sample(2).join + numbers.sample(3).join unless @robot_name
  end
  
  def reset
    remove_instance_variable(:@robot_name)
  end
end
