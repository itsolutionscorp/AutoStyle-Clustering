class Robot
  attr_reader :name

  def initialize
    @name = 2.times.map { ('A'..'Z').to_a[rand(26)] }.join + 3.times.map { rand(10) }.join
  end

  def reset
    initialize
  end
end
