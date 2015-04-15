class Robot
  def name
    @name ||= random_letter + random_letter + random_three_digit_number
  end
  
  def reset
    @name = nil
  end
  
  private
  def random_letter
    ('A'..'Z').to_a.sample
  end
  
  def random_three_digit_number
    (0..9).to_a.sample(3).join
  end
end
