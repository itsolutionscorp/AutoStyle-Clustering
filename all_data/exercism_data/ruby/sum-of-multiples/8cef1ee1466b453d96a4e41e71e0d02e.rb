class SumOfMultiples
  attr_reader :multiples

  class << self
    def to(number)
      new(3, 5).to(number)
    end
  end

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(number)
    sum = 0

    multiples.each { |multiple| sum += sum_multiples(multiple, number) }

    # Ensure uniqe multiples
    sum - sum_multiples(product_of_multiples, number)
  end

  private

  # See: http://stackoverflow.com/questions/4587320/project-euler-1find-the-sum-of-all-the-multiples-of-3-or-5-below-1000
  def sum_multiples(multiple, to)
     # Find the number of multiples that need to be summed
     n = (to - 1) / multiple

     # Use summation formula for (1..n)
     # See: http://en.wikipedia.org/wiki/Summation
     n * (n + 1)  / 2 * multiple   
  end

  def product_of_multiples
    multiples.reduce(:*)
  end
end
