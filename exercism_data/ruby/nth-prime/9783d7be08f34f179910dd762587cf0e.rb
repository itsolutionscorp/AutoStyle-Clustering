require 'prime'

class Prime
  def self.nth(number)
    fail ArgumentError, 'Invalid Input', caller if number <= 0
    EratosthenesSieve.instance.get_nth_prime(number - 1)
  end
end
