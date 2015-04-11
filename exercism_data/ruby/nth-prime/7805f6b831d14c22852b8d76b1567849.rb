require 'prime'

class Prime
  def self.nth(number)
    if number < 1
      raise ArgumentError
    end
    prime_count = 0
    i = 1
    while prime_count < number
      i += 1
      prime_count += 1 if Prime.prime?(i)
    end
    i
  end
end
