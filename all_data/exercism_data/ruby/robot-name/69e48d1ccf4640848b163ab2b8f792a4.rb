class Robot

  attr_accessor :name

  def initialize
    generate_name
  end

  def generate_name
    a = 2.times.map { ('A'..'Z').to_a.sample }.join
    b = 3.times.map { ('1'..'9').to_a.sample }.join
    @name = "#{a}#{b}"
  end

  def reset
    generate_name
  end
end
