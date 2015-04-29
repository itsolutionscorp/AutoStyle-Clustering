class Raindrops
  require 'prime'

  def self.convert(number)
    @raindrops = ''
    @factors = number.prime_division.flatten!

    if @factors.nil?
      1
    else
      find_rain(@factors)
    end
    @raindrops.empty? ? number.to_s : @raindrops
  end

  def self.find_rain(factors)
    factors.each do |n|
      if n == 3
        @raindrops += 'Pling'
      elsif n == 5
        @raindrops += 'Plang'
      elsif n == 7
        @raindrops += 'Plong'
      end
    end
  end
end
