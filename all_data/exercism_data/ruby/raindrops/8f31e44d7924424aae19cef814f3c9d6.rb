require 'prime'

class Raindrops
  def self.convert num
    str = num.prime_division.reduce('') do |memo,div|
      case div.first
      when 3
        memo << 'Pling'
      when 5
        memo << 'Plang'
      when 7
        memo << 'Plong'
      else
        memo
      end
    end

    str.empty? ? num.to_s : str
  end
end
