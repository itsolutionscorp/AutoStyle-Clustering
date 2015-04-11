class Robot

  LETTERS = [*('A'..'Z')]
  NUMBERS = [*(0..9)]

  @@robots = []

  attr_reader :name
  def initialize
    @name = generate_name
    register_name
  end

  def reset
    @name = generate_name
    register_name
  end

  private

  def generate_name
    LETTERS.sample(2).join + NUMBERS.sample(3).join
  end

  def register_name
    raise if @@robots.include?(@name)
    @@robots << @name
  end
end
