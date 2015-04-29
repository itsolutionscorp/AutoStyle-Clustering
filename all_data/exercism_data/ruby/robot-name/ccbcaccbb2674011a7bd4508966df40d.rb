class Robot
  attr_reader :name

  def initialize
    @name = "BC#{robot_number}"
  end

  def reset
    @name = "BC#{robot_number}"
  end

  def robot_number
    "#{rand(10)}#{rand(10)}#{rand(10)}"
  end
end
