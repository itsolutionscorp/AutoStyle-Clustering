class Robot
  def initialize
    @name = ('A'..'Z').to_a.shuffle[0,2].join + (0..9).to_a.shuffle[0,3].join
  end

  def name
    return @name
  end

  def reset
    name = @name
    loop do
      initialize
      break if name != @name
    end
  end
end
