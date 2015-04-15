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
    rand(100..999).to_s
  end
end
