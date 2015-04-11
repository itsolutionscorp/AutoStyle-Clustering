class Robot
  attr_accessor :name

  def initialize
    @name = random_name
  end

  def reset
    # prevents resetting to the same name
    reset if random_name == @name
    @name = random_name
  end

  private

  def random_name
    [
      random_letter,
      random_letter,
      rand(1000)
    ].join
  end

  def random_letter
    ('A'..'Z').to_a[rand(26)]
  end
end
