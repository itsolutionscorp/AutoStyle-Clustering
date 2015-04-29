require 'prime'
module Raindrops
  RAINDROPS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(n)
    if (noises = RAINDROPS.map { |prime, noise| noise if (n % prime).zero? }.compact).any?
      noises.join
    else
      n.to_s
    end
  end
end
