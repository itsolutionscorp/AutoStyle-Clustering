class SumOfMultiples

  def initialize(*nums)
    @nums = nums
  end

  def self.to limit
    new(3,5).to limit
  end

  def to limit
    @limit = limit
    @muliples = []
    get_multiples
    sum_multiples
  end

  def get_multiples
    @nums.each do |num|
      (1..@limit).each do |i|
        mult = num * i
        break if mult >= @limit
        @muliples << mult unless @muliples.include?(num * i)
      end
    end
  end

  def sum_multiples
    @muliples.inject(:+)
  end


end
