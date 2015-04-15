class SumOfMultiples

  def initialize(*args)
    @args = *args
  end

  @@sum = ->(args, i) { (1..i).select { |x| x != i && ( args.any? { |y| (x % y).zero? } ) }.inject(0, :+) }

  def self.to i
    @@sum.([3,5], i)
  end

  def to i
    @@sum.(@args, i)
  end

end
