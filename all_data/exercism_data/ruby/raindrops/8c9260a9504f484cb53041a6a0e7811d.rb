module Raindrops
  def self.convert(number)
    drops = [[3, 'Pling'], [5, 'Plang'], [7, 'Plong']].map do |prime, drop|
      drop if number % prime == 0
    end
    if drops.any?
      drops.join
    else
      number.to_s
    end
  end
end
