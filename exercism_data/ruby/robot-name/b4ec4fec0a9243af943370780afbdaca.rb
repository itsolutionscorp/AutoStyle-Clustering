class Robot
  attr_accessor :name
  def initialize
    generate_name!
  end

  def reset
    generate_name!
  end

  private
  def generate_name!
    @name  = ((0..1).map { (65 + rand(26)).chr } + (0..2).map { rand(10) }).join
  end
end
