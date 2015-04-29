require 'prime'

class Raindrops

  OUTPUTS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert x
    result = prime_factors(x).map { |e| OUTPUTS[e] }.join
    result.empty? ? x.to_s : result
  end

  private
  def self.prime_factors x
    x.prime_division.map { |f| f.first }.uniq
  end
end
