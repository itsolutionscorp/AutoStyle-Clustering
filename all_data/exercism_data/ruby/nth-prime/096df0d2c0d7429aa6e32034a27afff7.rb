class Prime
  def self.nth(target)
    raise ArgumentError if target == 0
    current_prime_candidate = 2
    index = 1

    while true
      if self.is_prime?(current_prime_candidate)
        return current_prime_candidate if index == target
        index += 1
      end
      current_prime_candidate += 1
    end
  end

  def self.is_prime?(num)
    (2..Math.sqrt(num)).each do |i|
      return false if num % i == 0
    end
  end
end
