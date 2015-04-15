# This doesn't catch names collisions 
# between different robots

class Robot
  attr_reader :name
    
  def initialize
    @names = [] if @names == nil
    @name = unique_robot_name
  end

  def reset
    @name = unique_robot_name 
  end
  
  private
  
  def unique_robot_name
    next while @names.include?(name = generate_name)
    @names << name
    return name
  end
  
  def generate_name
    (2.times.map { [*'A'..'Z'].sample } + 3.times.map { [*'0'..'9'].sample }).join
  end

end
