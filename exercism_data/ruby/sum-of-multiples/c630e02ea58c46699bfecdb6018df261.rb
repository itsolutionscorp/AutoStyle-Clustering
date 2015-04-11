class SumOfMultiples
  def initialize *ns
    @ns = ns
  end

  def to n
    (3...n).select(&method(:is_multiple)).reduce 0, :+
  end

  def self.to n
    (SumOfMultiples.new 3, 5).to n
  end

  private
  def is_multiple n
    @ns.detect { |i| n % i == 0 }
  end
end
