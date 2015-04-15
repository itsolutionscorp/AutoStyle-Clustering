class SumOfMultiples

  def initialize(*nums)
    @nums = nums
  end

  def self.to limit
    new(3,5).to limit
  end

  def to limit
    @limit = limit
    sum_of(multiples)
  end

  private
  
  def multiples
    array = [0]
    @nums.each do |num|
      (1..@limit).each do |i|
        mult = num * i
        break if mult >= @limit
        array << mult unless array.include?(num * i)
      end
    end
    array
  end

  def sum_of(array)
    array.inject(:+)
  end


end
