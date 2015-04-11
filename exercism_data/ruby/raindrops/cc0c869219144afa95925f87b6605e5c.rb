module Raindrops
  def self.convert(num)
    return num.to_s if num < 2

    primes = primes_below(num).uniq

    pf = primes.select do |x|
      (num % x).zero?
    end

    result = pf.map do |x|
      case x
      when 3
        'Pling'
      when 5
        'Plang'
      when 7
        'Plong'
      end
    end.join

    result.empty? ? num.to_s : result
  end

  private

  def self.is_prime?(num)
    for n in 2..num-1
      return false if (num % n).zero?
    end
    true
  end

  def self.primes_below(num)
    (2..num).select { |x| is_prime?(x) }
  end
end
