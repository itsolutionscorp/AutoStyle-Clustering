class Robot
  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  def generate_name
    ((0..1).map { (65 + rand(26)).chr }.join) + ((0..2).map { rand(10) }.join)
  end

end
