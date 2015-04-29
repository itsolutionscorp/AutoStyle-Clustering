class Robot
  attr_reader :name

  def initialize
    @name = [*('A'..'Z')].sample(2).join + [*(0..9)].sample(3).join.to_s
  end

  def reset()
    old_name = @name
    until @name != old_name
      @name = [*('A'..'Z')].sample(2).join + [*(0..9)].sample(3).join.to_s
    end
  end

end
