class Robot
  attr_accessor :name
  def initialize
    reset
  end
  def reset
    @name = generate_name
  end
  def generate_name
    [*'A'..'Z'].shuffle.take(2).join + ([*0..9].shuffle.take(3).join.to_s)
  end
end
