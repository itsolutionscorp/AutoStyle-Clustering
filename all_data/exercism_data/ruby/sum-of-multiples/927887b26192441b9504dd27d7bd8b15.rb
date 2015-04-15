class SumOfMultiples
  def initialize(*numbers)
      @divisors = numbers
  end

  def self.to(target)
    SumOfMultiples.new(3, 5).to(target)
  end

  def to(target)
    result = 0
    (1...target).each do |number|
      if divisible?(number)
        result += number
      end
    end
    result
  end

  private

  def divisible?(number)
    @divisors.inject(false) {|acc, divisor| acc || number % divisor == 0}
  end
end
