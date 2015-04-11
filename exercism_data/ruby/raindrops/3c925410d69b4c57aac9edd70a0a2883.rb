require 'prime'
class Raindrops
  TRANS_KEY = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert num
    factors = num.prime_division
    translate(factors).empty? ? num.to_s : translate(factors)
  end

  def self.translate factor
    output = String.new
    factor.flatten.collect { |f| output << TRANS_KEY[f] unless TRANS_KEY[f].nil? }
    output
  end


end
