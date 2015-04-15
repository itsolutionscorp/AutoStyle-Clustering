class Robot
  attr_accessor :name

  def initialize
    @name = generate_random_name
  end

  def reset
    @name = generate_random_name
  end

  private

  def generate_random_name
    ((0...2).map{ [*'A'..'Z'].sample }+(0...3).map{ [*'0'..'9'].sample }).join
  end
end
