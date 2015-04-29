require 'prime'

class Raindrops
  def self.convert(num)
    array = num.prime_division
    result = []

    if num == 1
      return "1"
    else
      array.each do |pair|
        if pair.include?(3)
          result << 'Pling'
        elsif pair.include?(5)
          result << 'Plang'
        elsif pair.include?(7)
          result << 'Plong'
        end
      end
    end

    unless result.empty?
      result.join
    else
      num.to_s
    end

  end
end
