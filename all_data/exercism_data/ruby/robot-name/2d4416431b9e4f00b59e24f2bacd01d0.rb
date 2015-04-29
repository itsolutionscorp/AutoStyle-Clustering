class Robot
  attr_reader :name

  def initialize
    alpha = ('A'..'Z').to_a
    numeric = (0..9).to_a.map{|i|i.to_s}
    @name = (alpha.sample(2) + numeric.sample(3)).join
  end

  def reset; initialize; end
end
