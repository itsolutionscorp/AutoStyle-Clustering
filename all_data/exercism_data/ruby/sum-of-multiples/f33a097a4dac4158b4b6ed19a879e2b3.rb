class SumOfMultiples
  attr_reader :multiples

  def initialize(*multiples)
    @multiples = multiples.empty? ?  [3, 5] : multiples
  end

  def to(number)
    max = number - 1
    (1..max).inject(0) do |memo, i|
      multiples.each do |multiple|
        if i % multiple == 0
          memo = memo + i
          break
        end
      end

      memo
    end
  end

  class << self
    def to(number)
      new.to(number)
    end
  end
end
