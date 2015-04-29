class SumOfMultiples
  attr_reader :multiples

  def initialize(*args)
    @multiples = args
  end

  def to(limit)
    return 0 if limit < multiples.min
    multiples.min.upto(limit - 1).reduce do |sum , n|
      sum + (multiples.any? { |m| n % m == 0 } ? n : 0)
    end
  end

  def self.to(limit)
    return 0 if limit < 3
    3.upto(limit - 1).reduce do |sum, n| 
      sum + (multiple_of_3_or_5?(n) ? n : 0)
    end
  end

  private
  def self.multiple_of_3_or_5?(n)
    n % 3 == 0 || n % 5 == 0
  end
end
