class Robot
  attr_reader :name
    
  ROBOT_NAMES =[]
  
  def initialize
    reset
   end

  def name_matches_a_previous_robot_name
    ROBOT_NAMES.each { |name| return true if name == @name }
    return false
  end
  
  def generate_name
    (2.times.map { [*'A'..'Z'].sample } + 3.times.map { [*'0'..'9'].sample }).join
  end
  
  def reset
    @name = generate_name 
    while name_matches_a_previous_robot_name
      @name = generate_name 
    end
    ROBOT_NAMES << @name
  end
  
end
