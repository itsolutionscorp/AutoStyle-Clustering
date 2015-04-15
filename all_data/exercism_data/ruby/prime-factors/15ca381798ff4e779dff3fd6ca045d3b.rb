require 'prime'

class PrimeFactors
  def self.for(n)
    Enumerator.new do |yielder|
      loop do
        break if n == 1
        factor = Prime.find { |i| n % i == 0 }
        yielder.yield factor
        n /= factor
      end
    end.to_a
  end
end
