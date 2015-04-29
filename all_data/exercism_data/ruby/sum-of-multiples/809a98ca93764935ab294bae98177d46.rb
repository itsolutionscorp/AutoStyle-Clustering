class SumOfMultiples

  def initialize(multiple1=3, multiple2=5, multiple3=0)
    @multiple1 = multiple1
    @multiple2 = multiple2
    @multiple3 = multiple3

  end
  
  def self.to(top_of_range)
    range = (1..top_of_range)
    sum = 0

    range.each do |value|
      sum += value if value % 3 == 0 || value % 5 == 0 && value != top_of_range
    end

    sum
  end

  def to(top_of_range)
    range = (1..top_of_range)
    sum = 0
    @multiple1 = top_of_range if @multiple1 == 3
    @multiple2 = top_of_range if @multiple2 == 5
    @multiple3 = top_of_range if @multiple3 == 0

    range.each do |value|
      sum += value if value % @multiple1 == 0 || value % @multiple2 == 0 || value % @multiple3 == 0 && value != top_of_range
    end

    sum
  end

end
