class Robot
  attr_reader :name

  def initialize
    create_name
  end

  def create_name
    letters = ("A".."Z").to_a.sample(2).join
    digits = 3.times.map{rand(10)}.join
    @name = letters + digits
  end

  def reset
    create_name
  end

end
