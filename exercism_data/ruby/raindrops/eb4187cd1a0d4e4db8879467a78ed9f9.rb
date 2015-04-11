require 'prime'

class Raindrops
  attr_reader :word_map
  @word_map = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }
  def self.convert(num)
    output = num.prime_division.reduce("") do |output, prime_arr|
      output + @word_map.fetch(prime_arr[0], "")
    end
    output.empty? ? num.to_s : output
  end
end
