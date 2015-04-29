class SumOfMultiples

  def initialize(*tests)
    @tests = tests
  end

  def to(limit)
    multiples = (0...limit).to_a
    multiples.delete_if { |x| is_a_multiple?(x, @tests) }
    multiples.reduce(:+)
  end

  def self.to(limit)
    self.new(3,5).to(limit)
  end

  private
    def is_a_multiple?(number, tests)
      tests.each { |test| return false if number % test == 0 }
      true
    end
end
