class SumOfMultiples
  def initialize(*args)
    @factors = *args
  end
  def self.to(num)
    SumOfMultiples.new(3, 5).to(num)
  end
  def to(num)
    multiples_array = []
    (1..num -1 ).each {|x| 
      @factors.each {|y|
        multiples_array.push(x) if x % y == 0
      }
    }
    result = 0
    multiples_array.uniq.each {|z| result += z}
    result
  end
end
