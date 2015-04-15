class Robot
  attr_reader :name

  def initialize
    @name = robot_name
  end

  def reset
    @name = robot_name
  end

  private

    def robot_name
      name = 2.times.map{ [*'A'..'Z'].sample }.join
      name << rand(100).to_s.rjust(3, "0")
    end
end
