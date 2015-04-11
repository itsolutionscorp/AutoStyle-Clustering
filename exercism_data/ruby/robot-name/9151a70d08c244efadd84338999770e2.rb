class Robot
  attr_reader :name

  def initialize
    @name = (alpha_sample(2) + numeric_sample(3)).join
  end

  def reset; initialize; end

  private

  def alpha_sample count
    alpha = []
    count.times { alpha << ('A'..'z').to_a.sample }
    alpha
  end

  def numeric_sample count
    numeric = []
    count.times { numeric << (0..9).to_a.map(&:to_s) }
    numeric
  end
end
