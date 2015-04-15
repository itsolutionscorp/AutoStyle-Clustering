class Robot

  attr_reader :name
  
  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end
  
  private
  
  def generate_name
    random_two_letter_code + random_three_digit_code
  end
  
  def random_two_letter_code
    2.times.collect { ('A'..'Z').to_a[Random.rand(0..25)] }.join
  end
  
  def random_three_digit_code
    3.times.collect { (0..9).to_a[Random.rand(0..9)] }.join
  end
    
  
end
