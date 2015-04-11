class SumOfMultiples
  attr_reader :div

  def initialize(first = 3, second = 5, third = nil)
    @div = []
    @div << first
    @div << second
    @div << third unless third == nil
  end

  def to(input)
    sum = []
    (0..input-1).each do |num|
      if div.count == 2
        if (num % div.first == 0)|| (num % div[1] == 0)
          sum << num
        end
      elsif div.count == 3 
        if (num % div.first == 0)|| (num % div[1] == 0) || (num % div[2] == 0)
          sum << num
        end
      end
    end

    sum.inject(:+)
  end

  def self.to(input)
    #take the input and count upto it
    sum = []
    (0...input).each do |num|
      if (num % 3 == 0)|| (num % 5 == 0)
        sum << num
      end
    end

    sum.inject(:+)
  end

end
