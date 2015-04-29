module Raindrops
  def self.convert(i)
    filtered_factors = generate_prime_factor(i) & [3, 5, 7]
    if filtered_factors.empty?
      i.to_s
    else
      filtered_factors.join("").sub("3", "Pling").sub("5", "Plang").sub("7", "Plong")
    end
  end

  def self.generate_prime_factor(n)
    @prime_factor ||= { 1 => [] }
    if n != 1
      factor = (2..n).find {|x| n % x == 0}
      @prime_factor[n] = [factor] + generate_prime_factor(n / factor)
    end
    @prime_factor[n]
  end
end
