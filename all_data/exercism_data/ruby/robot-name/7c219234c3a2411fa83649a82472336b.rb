class Robot
  attr_reader :name

  def initialize
    @name = "#{("A".."Z").to_a.sample(2).join}" + "#{(1..10).to_a.sample(3).join}"
  end

  def reset
    @name = "#{("A".."Z").to_a.sample(2).join}" + "#{(1..10).to_a.sample(3).join}"
  end
end
