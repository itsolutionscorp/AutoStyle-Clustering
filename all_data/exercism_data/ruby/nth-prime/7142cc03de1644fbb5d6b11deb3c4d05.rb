require 'Prime'

class Prime

  def self.nth(num)
    raise ArgumentError if num <= 0

    nth_prime = 0

      each_with_index do |prime, index|
        if index == num - 1
          nth_prime = prime
          break
        end
    end

    nth_prime
  end

end
