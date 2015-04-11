class Sieve
  attr_reader :limit
  def initialize num
    @nums = Array (2..num)
  end

  def primes
    @nums.each do |i|
      @nums.delete_if { |n| (n % i).zero? and n != i }
    end
    @nums
  end
end
