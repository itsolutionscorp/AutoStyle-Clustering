class SumOfMultiples
  attr_reader :multiples

  def initialize(*multiples)
    @multiples = multiples
  end

  def self.to(max)
    (1...max).inject(0) do |result, element|
      result += element if element % 3 == 0 || element % 5 == 0
      result
    end
  end

  def to(max)
    (1...max).inject(0) do |result, element|
      @multiples.each do |multiple|
        if element % multiple == 0
          result += element
          break
        end
      end
      result
    end
  end

end
