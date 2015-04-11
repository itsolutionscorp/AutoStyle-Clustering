class Prime

  def self.nth(num)
    limit = num * 11
    possibles = (2..limit).to_a
    primes = [2,3,5,7,11,13]
    divisors = (2..num).to_a

    possibles.each do |is_prime|
      if divisors.any? { |n| is_prime % n == 0 } == false
        primes << is_prime
      end
    end

    primes[num + 1]
  end
end

Prime.nth(1000)


# def prime?(n)
#   (2..Math.sqrt(n).to_int).none? do |number|
#     n % (number) == 0
#   end
# end

# def nth(n)
#   Enumerator.new do |yielder|
#     num = 2
#     loop do
#       yielder << num if prime? num
#       num += 1
#     end
#   end.take(n).last
# end

# puts nth(10001)
