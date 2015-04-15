class Series
  attr_accessor :ds
  def initialize(ds)
    @ds = ds.chars.map(&:to_i)
  end

  def slices(num)
    raise ArgumentError if num > ds.size
    ds.each_cons(num).inject([]) { |arr, a| arr.push(a) }
  end
end
