require 'prime'

class Prime

  def self.nth nth_prime

    if nth_prime < 1
      raise ArgumentError
    else
      list_prime_numbers = self.first nth_prime 
      list_prime_numbers[nth_prime-1]
    end

  end

end
