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
    ('A'..'Z').to_a[Random.rand(0..25)] + ('A'..'Z').to_a[Random.rand(0..25)]
  end
  
  def random_three_digit_code
    Random.rand(100..999).to_s
  end
    
  
end
