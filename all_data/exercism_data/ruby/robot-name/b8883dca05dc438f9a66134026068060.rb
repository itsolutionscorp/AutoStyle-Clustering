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
     random_chars + random_numbers
  end

  def random_chars
    ('A'..'Z').to_a.sample(2).join
  end
  
  def random_numbers
    rand(100...999).to_s
  end
end
