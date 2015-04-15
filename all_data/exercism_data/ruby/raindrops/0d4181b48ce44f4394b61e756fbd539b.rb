module Raindrops
  def self.convert(n)
    drops = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }.map { |prime, word| word if n % prime == 0 }.compact.join

    drops.empty? ? n.to_s : drops
  end
end
