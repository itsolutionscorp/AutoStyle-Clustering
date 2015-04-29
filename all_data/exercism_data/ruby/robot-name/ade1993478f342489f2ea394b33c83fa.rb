class Robot
  def name
    @name ||= initialize_name
  end

  def reset
    @name = nil
  end

  private

  def initialize_name
    digits = []
    digits << ('A'..'Z').to_a.shuffle[0,2]
    digits << rand(100..999)
    digits.join
  end
end
