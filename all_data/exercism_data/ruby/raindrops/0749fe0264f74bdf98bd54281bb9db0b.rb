class Raindrops
  CONVERSIONS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(n)
    converted = n.to_s
    if prime_factorization(n).include?(3) || prime_factorization(n).include?(7) || prime_factorization(n).include?(5) 
      converted = ''
      prime_factorization(n).uniq.each do |prime|
        unless CONVERSIONS[prime].nil?
          converted << CONVERSIONS[prime]
        end
      end
    end
    converted
  end

  def self.prime_factorization(n)
    number = n
    prime_factors = []
    divider = 2
    until number <= 2
      if number % divider == 0 && is_prime?(divider)
        prime_factors << divider
        number = number / divider
      else
        divider += 1
      end
    end
    prime_factors
  end

  def self.is_prime?(n)
    return true if n == 2 
    return false if n.even?
    (3..(Math.sqrt(n).ceil)).each do |num|
       return false if n % num == 0 
    end
    true
  end
end
