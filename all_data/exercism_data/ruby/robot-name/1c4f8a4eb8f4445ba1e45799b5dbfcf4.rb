class Robot
  def initialize
    reset
  end

  def name
    @name
  end

  def reset
    @name = (0..1).map { (65 + rand(26)).chr }.join + (99+rand(900)).to_s
  end
end
