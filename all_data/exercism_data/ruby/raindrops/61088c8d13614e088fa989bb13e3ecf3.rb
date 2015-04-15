class Raindrops
  require 'prime'

  def self.convert(number)
    factors = number.prime_division.flatten!
    raindrops = ''

    if factors.nil?
      1
    else
      factors.each do |n|
        if n == 3
          raindrops += 'Pling'
        elsif n == 5
          raindrops += 'Plang'
        elsif n == 7
          raindrops += 'Plong'
        end
      end
    end

    raindrops.empty? ? number.to_s : raindrops
  end
end
