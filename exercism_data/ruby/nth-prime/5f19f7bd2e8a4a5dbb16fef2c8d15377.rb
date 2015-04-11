require 'pry'
require 'mathn'
$primes = [2, 3]
class Prime

  def self.nth(number)
    # binding.pry
    raise ArgumentError.new("No primes for zero.") if number == 0
    return $primes[number - 1] if number <= $primes.length

    $primes = Prime.first number
    return $primes.last
  end

  private

  def self.is_prime?(number)
    i = 0
    while (number >= $primes[i]**2 && $primes[i] != nil)
      return false if number % $primes[i] == 0
      i += 1
    end
    return true
  end

end
