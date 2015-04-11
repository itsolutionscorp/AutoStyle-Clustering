module Raindrops
  def self.convert(i)
    filtered_factors = generate_prime_factor(i) & [3, 5, 7]
    return i.to_s if filtered_factors.empty?
    drop = ""
    drop += "Pling" if filtered_factors.include?(3)
    drop += "Plang" if filtered_factors.include?(5)
    drop += "Plong" if filtered_factors.include?(7)
    drop
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
