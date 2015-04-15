class Robot

  attr_reader :name
  
  def name
    alpha_arr = ('A'..'Z').to_a
    alphabetic_str = alpha_arr.sample(2).join("")
    numeric_arr = (0..9).to_a
    numeric_str =  numeric_arr.sample(3).join("")
    @name ||= alphabetic_str+numeric_str
    @name
  end
  
  def reset
    @name = nil
  end
end

Robot.new.name
