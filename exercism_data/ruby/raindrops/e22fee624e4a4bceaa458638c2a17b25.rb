module Raindrops
  class << self
    FACTOR_APPEND_HASH = {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }

    def convert(num)
      raindrop_output = ""

      FACTOR_APPEND_HASH.each_pair do |factor, string|
        if has_as_factor?(num, factor)
          raindrop_output += string
        end
      end

      if raindrop_output.empty? 
        num.to_s 
      else
        raindrop_output
      end
    end

    private
    def has_as_factor?(number, factor)
      (number % factor).zero?
    end
  end
end
