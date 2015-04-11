class SumOfMultiples
  attr_reader :multiples

  @multiples = [3, 5]

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(num)
    self.class.calc(num, @multiples)
  end

  def self.to(num)
    self.calc(num, @multiples)
  end

  private

  def self.calc(num, multiples)
    (2...num).inject(0) do |sum, n|
      if self.multiple?(n, multiples)
        sum + n
      else
        sum
      end
    end
  end

  def self.multiple?(n, multiples)
    multiples.any? { |m| n % m == 0 }
  end
end
