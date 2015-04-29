class SumOfMultiples

  def initialize(*mults)
    @mults = mults
  end

  def to(n)
    (1...n).select do |x|
      eval condition_builder(@mults)
    end.inject(:+) || 0
  end

  def self.to(n)
    SumOfMultiples.new(3,5).to(n)
  end

  private

    def condition_builder(mults)
      mults.map { |n| "x % #{n} == 0"}.join(" || ")
    end
end
