class Raindrops

  def self.is_prime?(num)
    divisors = (2..num-1)
    divisors.each do |i|
      return false if num % i == 0
    end
    return true
  end

  def self.find_primes(num)
    primes_collection = [2]
    (3..num).each do |n|
      primes_collection << n if is_prime?(n)
    end
    primes_collection
  end

  def self.prime_factor(number)
    results = []
    find_primes(number).each do |test_prime|
      while number % test_prime == 0
        number = number / test_prime
        results << test_prime
      end
    end
    return [number] if results.empty?
    results
  end

  def self.convert(num)
    output_array = []
    prime_factor(num).uniq.uniq.each do |f|
      if f == 3
        output_array << "Pling"
      elsif f == 5
        output_array << "Plang"
      elsif f == 7
        output_array << "Plong"
      end
    end
    if output_array.empty?
      output_array << num.to_s
    end
    output_array.join
  end
end
