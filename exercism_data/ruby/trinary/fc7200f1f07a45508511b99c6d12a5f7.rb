class Trinary
  attr_accessor :ds
  def initialize(ds)
    @ds = ds.reverse.chars.map(&:to_i)
  end

  def to_decimal
    ds.each_with_index.inject(0) do |sum, (n, index)|
      sum + (n * (3 ** index))
    end
  end
end
