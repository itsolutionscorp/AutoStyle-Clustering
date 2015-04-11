require 'prime'

class Raindrops
  OUTPUTS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(number)
    converted_number = number.prime_division.flatten.map {|prime| OUTPUTS[prime] }.compact.uniq.join('')
    converted_number.empty? ? number.to_s : converted_number
  end
end
