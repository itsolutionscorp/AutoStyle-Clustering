class Robot
  attr_accessor :name

  def initialize
    reset
  end

  def reset
    @name = "RB#{Random.new.rand(100..999)}"
  end
end
