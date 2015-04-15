class Sieve
  def initialize max
    @range = (2..max)
    @candidates = Hash[@range.zip([true]*@range.count)]

    (2..Math.sqrt(max)).each do |prime|
      if @candidates[prime] then
        @candidates.each do |candidate, is_prime|
          @candidates[candidate] = false if candidate % prime == 0 && candidate > prime
        end
      end
    end
  end

  def primes
    @candidates.select{ |_, is_prime| is_prime }.keys
  end
end
