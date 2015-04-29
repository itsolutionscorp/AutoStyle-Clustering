class Robot
  def initialize
    @name = random_name
  end

  def name
    @name
  end

  def reset
    @name = random_name
  end

  def random_name
    ('A'..'Z').to_a.shuffle[0..1].join + (0..9).to_a.shuffle[0..2].join
  end
end
