class Robot

  def initialize
    self.reset
  end

  def name
    @robot_name
  end

  def reset
    @robot_name = ""

    for r in 1..2 do
      @robot_name += rand(65..90).chr
    end

    for r in 1..3 do
      @robot_name += rand(0..9).to_s
    end
  end

end
