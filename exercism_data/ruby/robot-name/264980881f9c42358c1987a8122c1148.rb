class Robot
  attr_accessor :name
  def initialize
    @name = (('A'..'Z').to_a.sample(2)+(1..9).to_a.sample(3)).join
  end

  def reset
    @name = (('A'..'Z').to_a.sample(2)+(1..9).to_a.sample(3)).join
  end
end