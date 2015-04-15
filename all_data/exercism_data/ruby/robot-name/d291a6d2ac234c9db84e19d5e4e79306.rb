class Robot
  @@robot_names = []
  
  attr_reader :name
  
  def initialize
    reset
  end
  
  def reset
    @name = random_name while @@robot_names.include?(@name) || @name.nil?
    @@robot_names << @name
  end
  
  
  private 
  
  def random_name
     random_two_chars + random_three_numbers
  end
  
  
  def random_two_chars
    (0..1).map { ("A".."Z").to_a[rand(26)] }.join 
  end
  
  def random_three_numbers
    (0..2).map { rand(0..9) }.join
  end
end
