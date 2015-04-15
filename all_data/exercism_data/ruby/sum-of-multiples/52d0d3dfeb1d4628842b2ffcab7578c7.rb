class SumOfMultiples
  def initialize(*args)
  	@args = args
  end

  def to(max)
  	sum = []
    @args.each do |m|
      for i in 0..max-1
      	sum << i if i % m == 0
      end
    end
    sum.uniq!.inject(:+)
  end

  def self.to(max)
    new(3, 5).to(max)
  end
end
