module Raindrops
  def self.convert(n)
    divisor_noise = [[3, 'Pling'], [5, 'Plang'], [7, 'Plong']]
    noises = divisor_noise.map { |div, noise| noise if n % div == 0 }.compact
    if noises.empty?
      n.to_s
    else
      noises.join
    end
  end
end
