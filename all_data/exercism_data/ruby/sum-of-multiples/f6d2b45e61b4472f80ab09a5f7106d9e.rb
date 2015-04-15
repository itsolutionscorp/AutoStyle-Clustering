class SumOfMultiples
  attr_reader :multiples

  def self.to(num)
    new.to(num)
  end

  def initialize(*multiples)
    @multiples = multiples.empty? ? [3, 5] : multiples
  end

  def to(num)
    1.upto(num - 1).select do |candidate|
      multiple_of_any?(candidate)
    end.reduce(:+) || 0
  end

  def multiple_of_any?(num)
    multiples.each do |multiple|
      return true if num % multiple == 0
    end
    false
  end
end
