class Prime
  
  def self.nth(target_position)
    raise ArgumentError if target_position < 1

    current_position = 1
    potential_prime_num = 2
    
    while current_position < target_position
      potential_prime_num += 1
      current_position += 1 if is_prime?(potential_prime_num) 
    end

    potential_prime_num
  end

  private

  def self.is_prime?(potential_prime_num)
    max_divisor = Math.sqrt(potential_prime_num).round(0)
    potential_divisors = (2..max_divisor).to_a
    is_divisible?(potential_prime_num, potential_divisors)
  end

  def self.is_divisible?(potential_prime_num, potential_divisors)
    is_prime = true

    potential_divisors.each do |divisor|
      if (potential_prime_num % divisor).zero?
        is_prime = false
        break
      end
    end

    is_prime
  end

end
