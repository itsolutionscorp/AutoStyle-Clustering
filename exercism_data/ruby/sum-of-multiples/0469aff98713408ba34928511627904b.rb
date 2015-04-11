class SumOfMultiples
  attr_accessor :multiples
  def initialize(*multiples)
    @multiples = *multiples
  end

  def self.to(num)
    nums = []
    (1..num-1).each do |x|
      if x % 3 == 0 || x % 5 == 0
        nums << x
      end
    end
    nums.inject(0, :+)
  end

  def to(num)
    nums = []
    multiples.each do |multiple|
      (1..num-1).each do |x|
        if x % multiple == 0
          nums << x
        end
      end
    end
    nums.uniq.inject(0, :+)
  end
end
