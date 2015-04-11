class Raindrops
  def initialize
  end

  def convert(n)
    @primes = []
    is_prime(n) unless n == 1
    if @primes.empty?
      return n.to_s
      puts "ITS EMPTY!"
    else
      total = ""
      if @primes.include?(3) 
        total += "Pling"
      end
      if @primes.include?(5)
        total += "Plang"
      end
      if @primes.include?(7)
        total += "Plong"
      end
      if total == ""
        return n.to_s
      end
      return total
    end
  end


  private

  def is_prime(n)
    (2..n).each do |i|
      if n%i == 0
        @primes << i
        is_prime(n/i)
        break
      end
    end
  end

end
