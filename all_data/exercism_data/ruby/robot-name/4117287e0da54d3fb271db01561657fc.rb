class Robot

  attr_accessor :name

  def initialize
    generate_name!
  end

  def reset
    generate_name!
  end

  protected

  def generate_name!
    @name = 2.times.collect{('A'..'Z').to_a.sample }.concat(3.times.collect{ (0..9).to_a.sample }).join
  end

end
