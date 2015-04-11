class SumOfMultiples

  def initialize(*nums)
    @nums = nums
  end

  def to num
    @limit = num
    @nums ||= [3,5]
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
    @muliples.inject(0, :+)
  end

  def self.to num
    @limit = num
    @nums ||= [3,5]
    @muliples = []
    get_multiples
    sum_multiples
  end

  def self.get_multiples
    @nums.each do |num|
      (1..@limit).each do |i|
        mult = num * i
        break if mult >= @limit
        @muliples << mult unless @muliples.include?(num * i)
      end
    end
  end

  def self.sum_multiples
    @muliples.inject(0, :+)
  end

end
