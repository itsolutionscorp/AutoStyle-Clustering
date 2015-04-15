class Raindrops
  def self.convert(num)
    fac = find_factors(num).uniq
    str = []
    fac.each do |i|
      str << gen_string(i)
    end

    if str.join.empty?
      str << gen_string(num)
    end

    if str.join.empty?
      str << num
    end
    str.join
  end

  private

    def self.gen_string(num)
      if num == 3
        'Pling'
      elsif num == 5
        'Plang'
      elsif num == 7
        'Plong'
      end
    end

    def self.get_primes(num)
      primes = [2,3]
      is_prime = false

      (4..Math.sqrt(num).floor).each do |i|
        (0..primes.length - 1).each do |j|
          if i % primes[j] == 0
            is_prime = false
            break
          else
            is_prime = true
          end
        end
        if is_prime == true
          primes << i
        end
      end
      primes
    end

    def self.find_factors(num)
      n = num
      primes = get_primes(num)
      factors = []

      while n > 1
        (0..primes.length-1).each do |k|
          if n % primes[k] == 0
            n = n / primes[k]
            factors << primes[k]
            redo
          end
        end

        if n == num
          n = 1
        end

        if n > Math.sqrt(num).floor
          factors << n
          n = 1
        end
      end
      factors
    end
end

puts Raindrops.convert(52)
