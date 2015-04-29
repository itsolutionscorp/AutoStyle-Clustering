require 'prime'

class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    numbers = [nil, nil, *2..@limit]
    numbers.each do |number|
      next if number.nil?
      break if number**2 > @limit
      (number**2).step(by: number, to: @limit) do |multiple|
        numbers[multiple] = nil
      end
    end
    numbers.compact
  end
end
