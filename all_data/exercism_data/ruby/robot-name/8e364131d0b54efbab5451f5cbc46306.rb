class Robot

  attr_reader :name

  def initialize 
    create_name
  end

  def reset
    create_name
  end

  def create_name
    first_letter  = ('A'..'Z').to_a.sample
    second_letter = ('A'..'Z').to_a.sample
    first_num     = rand(10)
    second_num    = rand(10)
    third_num     = rand(10)
    
    @name = "#{first_letter}#{second_letter}#{first_num}#{second_num}#{third_num}"
  end

end
