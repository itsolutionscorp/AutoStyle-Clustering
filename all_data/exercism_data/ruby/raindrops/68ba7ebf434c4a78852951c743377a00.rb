class Raindrops
  PRIMES = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71]

  RAINDROPS = [
    [3, 'Pling'],
    [5, 'Plang'],
    [7, 'Plong']
  ]

  def self.convert(num)
    factors = get_unique_primes(num)
    result = RAINDROPS.map do |raindrop|
      factors.include?(raindrop[0]) ? raindrop[1] : ''
    end.join
    result.empty? ? num.to_s : result
  end

  def self.get_unique_primes(num)
    # puts "--#{num}--"
    factors = []
    until num == 1
      PRIMES.each do |prime|
        if (num % prime == 0)
          factors << prime
          num /= prime
        end
      end
    end
    factors.sort.uniq
  end
end

# %w(2 3 4 5 6 7 15 21 35 105).each { |n| puts Raindrops.convert(n.to_i) }
