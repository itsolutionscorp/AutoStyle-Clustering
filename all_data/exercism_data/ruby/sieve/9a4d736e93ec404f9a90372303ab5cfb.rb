class Sieve
  def initialize border
    @border = border
    @primes = {}
    2.upto(border).each{|i| @primes[i] = true}
  end

  def primes
    sieve 2
    @primes.select{|k,v| v == true}.keys.map{|num| num.to_s.to_i}
  end
  
  private

  def sieve key
    (key*2..@border).step(key){|i| @primes[i] = false}
    next_key = @primes.select{|k,v| v == true && k > key}.first
    next_key.nil? ? return : sieve(next_key[0].to_s.to_i)
  end
end
