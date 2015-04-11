class SumOfMultiples
  def initialize(*args)
    @multi_array = args
  end

  class << self
    def to(max)
      sum_of_mul_3_and_5(max).inject do |result, val|
        result += val
      end
    end

    def sum_of_mul_3_and_5(to)
      (multiples_of(3, to) + multiples_of(5, to)).uniq
    end

    def multiples_of(num, to)
      return 0 if num <= 0 || num == 0
      result = []
      0.upto(to - 1).inject(result) do |result, val|
        result.push val if val % num == 0
        result
      end
      result
    end
  end

  def to(max)
    sum_array = []
    @multi_array.each do |multiple|
      sum_array += SumOfMultiples.multiples_of(multiple, max)
    end

    sum_array.uniq.inject do |sum, val|
      sum += val
    end
  end
end
