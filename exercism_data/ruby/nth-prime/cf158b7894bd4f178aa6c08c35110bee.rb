require 'prime'

class Prime
  def self.nth(prime_number)
    raise ArgumentError if prime_number <= 0

    Prime.first(prime_number).last
  end
end
