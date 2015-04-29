require 'pry'

class Sieve
  def initialize(num)
    @num = num
  end

  def primes
    s = (0..@num).to_a
    s[0] = s[1] = nil
    s.each do |p|
      next unless p
      break if p * p > @num
      (p*p).step(@num, p) { |m| s[m] = nil }
    end
    s.compact
  end
end
