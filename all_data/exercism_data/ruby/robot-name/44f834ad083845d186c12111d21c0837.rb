class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def generate_name
    (('a'..'z').to_a.sample(2) + (0..9).to_a.sample(3)).join
  end

  def reset
    self.name = generate_name
  end

end
