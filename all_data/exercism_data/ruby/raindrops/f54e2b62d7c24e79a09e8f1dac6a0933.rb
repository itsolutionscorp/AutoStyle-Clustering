class Raindrops
  def self.convert(num)
    ans = ''
    require 'Prime'
    primes = num.prime_division.flatten.uniq
    return num.to_s unless primes.any? { |x| [3, 5, 7].include?(x) }
    primes.each do |x|
      if x == 3
        ans += 'Pling'
      elsif x == 5
        ans += 'Plang'
      elsif x == 7
        ans += 'Plong'
      end
    end
    ans
  end
end
