require 'prime'
require 'pry'

class Raindrops

  class << self
    def convert(num)
      
      output = num.prime_division.flatten.uniq.each_with_object("") do |factor, acc|
        if raindrop_speak?(factor)
          acc << raindrop_speak?(factor)
        end
      end

      output << num.to_s if output.empty?

      output
    end

    def raindrop_speak?(factor)
      case factor
      when 3
        "Pling"
      when 5
        "Plang"
      when 7
        "Plong"
      end
    end
  end
end
