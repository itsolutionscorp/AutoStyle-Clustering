class Prime
  class << self
    def nth(num)
      raise ArgumentError.new("Please enter a valid number.") unless num > 0
      prime_list[num - 1]
    end

    private

    def prime_list
      return @@primes unless @@primes.empty?
      nums = (2..105000).to_a
      nums.each { |num| nums.reject! { |x| x % num == 0 && x != num } }
      @@primes = nums
    end

    @@primes = []
  end
end
