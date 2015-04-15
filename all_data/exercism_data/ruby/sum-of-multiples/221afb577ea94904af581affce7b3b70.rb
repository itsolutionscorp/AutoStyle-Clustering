class SumOfMultiples
  def initialize(*tests)
    @tests = tests
  end

  def self.to(upper_limit)
    upper_limit >= 3 ? SumOfMultiples.new(3, 5).to(upper_limit) : 0
  end

  def to(upper_limit, tests = @tests, multiples = {})
    return multiples.keys.inject(:+) if tests.empty?
    if tests.first < upper_limit
      (tests.first..(upper_limit - 1)).each do |num|
        multiples[num] = 1 if num % tests.first == 0
      end
    else
      multiples[0] = 1
    end
    to(upper_limit, tests.drop(1), multiples)
  end
end
