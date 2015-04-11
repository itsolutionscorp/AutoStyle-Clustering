class SumOfMultiples

  def self.to(limit)
    new(5, 3).to(limit)
  end

  def to(number)
    sum = 0
    (1...number).each do |n|
      numbers.each do |m|
        if (n % m == 0)
          sum += n
          break
        end
      end
    end
    sum
  end

  attr_reader :numbers

  def initialize(*args)
    @numbers = args
  end

end
