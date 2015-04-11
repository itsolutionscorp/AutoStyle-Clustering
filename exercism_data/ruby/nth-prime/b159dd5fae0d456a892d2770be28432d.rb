class Prime
  def self.nth(n)
    raise ArgumentError.new if n <= 0

    primes = Enumerator.new do |yielder|
      k = 1
      while true
        if prime?(k)
          yielder << k
        end
        k += 1
      end
    end

    primes.first(n).last
  end

  def self.prime?(n)
    if n < 2
      false
    elsif n < 4
      true
    elsif n.modulo(2) == 0 || n.modulo(3) == 0
      false
    else
      (5..(Math.sqrt(n).to_i + 1)).find { |k| n.modulo(k) == 0 }.nil?
    end
  end
end
