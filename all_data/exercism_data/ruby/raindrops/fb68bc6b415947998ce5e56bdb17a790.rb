require 'prime'

class Raindrops
  INSTRUCTIONS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(num)
    prime_numbers = num.prime_division.flatten.select{|n| [3, 5, 7].include?(n)}

    if prime_numbers.any?
      return prime_numbers.map { |pm| INSTRUCTIONS[pm] }.join("")
    else
      return num.to_s
    end

  end
end
