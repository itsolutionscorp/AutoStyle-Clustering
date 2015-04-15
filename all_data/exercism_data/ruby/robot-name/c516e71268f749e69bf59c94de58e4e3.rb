class Robot

  attr_reader :name

  def initialize 
    first_letter  = (65 + rand(26)).chr
    second_letter = (65 + rand(26)).chr
    first_num     = rand(10)
    second_num    = rand(10)
    third_num     = rand(10)
    @name = "#{first_letter}#{second_letter}#{first_num}#{second_num}#{third_num}"
  end

  def reset
    @name = Robot.new.name
  end

end
