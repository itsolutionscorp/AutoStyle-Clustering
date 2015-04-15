require 'prime'

class Raindrops
  FACTORS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  
  def self.convert n
    tokens = n.prime_division.map { |prime,| FACTORS[prime] }.compact
    tokens.empty? ? n.to_s : tokens.join
  end
end
