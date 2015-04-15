class Robot

  def initialize
    @name = nil
  end

  def name
    @name ||= reset
  end

  def reset
    name = ''
    2.times {name += ('A'..'Z').to_a.sample}
    3.times {name += rand(9).to_s}
    @name = name
  end
end
