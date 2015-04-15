require 'prime'
class Raindrops

  def self.convert(n)
    drops = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    str = prime_factors(n).collect{ |i| drops[i] }.compact.join
    str.empty? ? n.to_s : str 
  end

  def self.prime_factors(n)
    (1..n).select{ |i| n%i==0 && Prime.prime?(i)}
  end

end
