class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = random_name
  end

  private

  def random_name
    ('A'..'Z').to_a.sample(2).join + "%03d" % rand(999)
  end
end
