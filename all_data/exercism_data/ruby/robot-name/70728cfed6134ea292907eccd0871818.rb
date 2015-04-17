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
    '' << random_two_letter_code << random_three_digit_code
  end
  
  def random_two_letter_code
    ('A'..'Z').to_a.sample(2).join
  end
  
  def random_three_digit_code
    (0..9).to_a.sample(3).join
  end
    
  
end