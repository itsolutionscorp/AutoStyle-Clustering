require 'prime'

class Raindrops
  def self.convert(number)
    division = Prime.prime_division(number).flatten

    if (division - mappings.keys) == division
      number.to_s
    else
      division.to_s.chars.map{|c| mappings[c.to_i]}.join
    end
  end

  private
  def self.mappings
    {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
  end
end
