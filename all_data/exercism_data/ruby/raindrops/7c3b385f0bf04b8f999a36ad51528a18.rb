require 'prime'

class Raindrops
  def self.convert number
    message = translate_to_rainspeak(number.prime_division.flatten)
    message[0] ? message : number.to_s
  end

  def self.translate_to_rainspeak prime_division
    rosetta_stone = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    prime_division.map { |x| rosetta_stone[x] }.compact.join("")
  end
end
