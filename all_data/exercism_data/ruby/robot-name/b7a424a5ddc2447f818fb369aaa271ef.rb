class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = "ab#{rand(10000)}"
  end
end
