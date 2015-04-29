class PrimeFactors
  require 'Prime'
  def self.for(number)
    ans = []
    5.times do |x|
      Prime.each(number) do |prime|
        number % prime == 0 ? ans << prime : next
        number = number / prime
        break if prime > 10000
      end
    end
    ans
  end
end
