class Robot

  attr_reader :name

  def initialize 
    rand_char = (65 + rand(26)).chr
    rand_numb = rand(10)

    first_letter  = rand_char
    second_letter = rand_char
    first_num     = rand_numb
    second_num    = rand_numb
    third_num     = rand_numb
    
    @name = "#{first_letter}#{second_letter}#{first_num}#{second_num}#{third_num}"
  end

  def reset
    @name = Robot.new.name
  end

end
