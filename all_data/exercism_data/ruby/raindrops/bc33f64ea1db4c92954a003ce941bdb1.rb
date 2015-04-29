module Raindrops
  def self.convert num
    trans = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

    result = trans.map { |k, v| v if num % k == 0 }.join
    result.empty? ? num.to_s : result
  end
end
