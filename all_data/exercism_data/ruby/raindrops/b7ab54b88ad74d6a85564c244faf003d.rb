class Raindrops
  def self.convert(dividend)
    return "1" if dividend == 1
    primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43]
    prime_factors = []
    temp = dividend
    index = 0
    loop do
      if temp % primes[index] == 0
        prime_factors << primes[index]
        temp /= primes[index]
        break if temp == 0
      else
        index += 1
      end
      break if primes.last == primes[index]
    end
    result = []
    prime_factors.uniq.map do |element|
      result << 'Pling' if element == 3
      result << 'Plang' if element == 5
      result << 'Plong' if element == 7
    end
    return dividend.to_s if result.empty?
    result.join.to_s
  end

end
