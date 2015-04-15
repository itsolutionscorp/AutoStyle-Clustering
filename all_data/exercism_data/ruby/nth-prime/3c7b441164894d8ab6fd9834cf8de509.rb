require 'prime'

class Prime
  # Thank you ruby for making this super easy.
  def self.nth(count)
    fail ArgumentError, 'must be positive' if count < 1

    take(count).last
  end
end

# Pseudo-code for a more manual process.  Come back to this.
# while the length of primes < requested_prime
#   count up from 2 (current_check)
#   divide current_check by known primes to determine if current_check is prime
#   if there is no remainder, add to primes
#   else increment current_check by 1 and run recursively
# Return last number in primes array
