class Robot

  attr_accessor :name
  
  def initialize
    generate_name
  end

  def reset
    generate_name
  end

  private 

  def generate_name
    self.name = (('A'..'Z').to_a.sample(2) + ('0'..'9').to_a.sample(3)).join
  end

end
