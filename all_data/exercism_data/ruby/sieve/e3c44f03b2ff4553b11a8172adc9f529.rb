class Sieve
  def initialize(num)
    @eratosthenes_array = (2..num).to_a
  end

  def primes
    e = @eratosthenes_array
    (0...e.length).each do |i|
      if e[i]
        ((i+1)...e.length).each do |j|
          if e[j] && e[j] % e[i] == 0
              e[j] = nil
          end
        end
      end
    end
    e.compact
  end
end
