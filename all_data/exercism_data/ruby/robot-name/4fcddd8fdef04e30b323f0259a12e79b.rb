class Robot
  def initialize
    @name = name_generate
  end

  def name
    @name ||= initialize
  end

  def reset
    @name = nil
  end

  private

  def name_generate
    [*('A'..'Z')].sample(2).join + [*(0..9)].sample(3).join
  end
end
