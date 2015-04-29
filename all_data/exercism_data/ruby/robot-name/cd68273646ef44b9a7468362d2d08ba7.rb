class Robot

  attr_reader :name

  def initialize 
    robot_name_numb = rand(10)
    rand_char = ('A'..'Z').to_a.sample

    first_letter  = rand_char
    second_letter = rand_char
    first_num     = robot_name_numb
    second_num    = robot_name_numb
    third_num     = robot_name_numb
    
    @name = "#{first_letter}#{second_letter}#{first_num}#{second_num}#{third_num}"
  end

  def reset
    @name = Robot.new.name
  end

end
