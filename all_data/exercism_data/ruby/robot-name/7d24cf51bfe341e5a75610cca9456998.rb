class Robot
  # attr_accessor :name
  
  def name
    @name ||= generate_name
  end

  def reset
    @name = generate_name
  end

  private 
  def generate_name
    ascii_chars = ('A'..'Z').to_a
    letters = (0..1).map { ascii_chars[rand(ascii_chars.size)] }.join 
    numbers = (0..2).map {|number| rand(9)}.join
    letters + numbers
  end
  
end
