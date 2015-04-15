class Sieve
  attr_reader :num, :range

  def initialize(num)
    @num = num
    @range = (2..num).to_a
  end

  def primes
    (2..num).each do |n|
      range.delete_if {|i| i % n == 0 && i > n }
    end
    range
  end
end
