require 'prime'
class Prime
  @primes_list = Prime.first 1000
	def self.nth(n)
    if n > @primes_list.length
      @primes_list = Prime.first n
    end
    if !(n.is_a?(Fixnum) & (n > 0))
      raise ArgumentError.new("not a valid argument")
    else
      @primes_list[n-1]
    end
  end
end
