class Prime
  def self.nth(n)
    raise ArgumentError if n == 0
    return 2 if n == 1
    current_number = 3
    i = 1

    until i == n
      i += 1 if Prime.is_prime?(current_number)
      current_number += 1 if i < n
    end

    current_number
  end

  def self.is_prime?(number)
    (2..number-1).each do |i|
      return false if number % i == 0
    end

    true
  end
end

p Prime.nth(7)
