class Prime
  @@primes = [2, 3]

  def self.nth(n)
    fail ArgumentError, 'Please provide a natural number.' if n < 1
    search(n) if n > @@primes.length
    @@primes[n - 1]
  end

  def self.search(n)
    nb = @@primes.last + 2
    loop do
      @@primes << nb if AKS.prime?(nb)
      return if AKS.prime?(nb) && @@primes.length == n
      nb += 2
    end
  end
end

class AKS
  @@checked = {}

  def self.prime?(nb)
    return @@checked[nb] if @@checked.include? nb
    return false if nb < 2
    coeff = x_minus_1_to_the nb
    coeff[0] += coeff.pop
    res = coeff.all? { |n| n % nb == 0 }
    @@checked[nb] = res
    res
  end

  def self.x_minus_1_to_the(n)
    n.times.inject([1]) { |a, _e| ([0] + a).zip(a + [0]).map { |x, y| x - y } }
  end
end
