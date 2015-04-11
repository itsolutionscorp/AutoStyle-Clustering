class SumOfMultiples
  def initialize *ns
    @ns = ns
  end

  def to n
    (3...n).select(&method(:multiple?)).reduce 0, :+
  end

  def self.to n
    new(3, 5).to n
  end

  private
  def multiple? n
    @ns.detect { |i| n % i == 0 }
  end
end
