class Robot
  attr_accessor :name
  
  def name
    @name ||= generate_name
  end

  def reset
    @name = generate_name
  end

  private 
  def generate_name
    (0..1).map { (65 + rand(26)).chr }.join + (0..2).map {|item| rand(9)}.join
  end
  
end
