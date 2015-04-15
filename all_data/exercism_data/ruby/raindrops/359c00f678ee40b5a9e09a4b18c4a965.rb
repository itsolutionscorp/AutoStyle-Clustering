require 'prime'

class Raindrops
  def self.convert(number)
    string = ""

    number.prime_division.each do |prime|
      case prime.first
      when 3
        string << "Pling"
      when 5
        string << "Plang"
      when 7
        string << "Plong"
      end
    end
    return string.empty? ? number.to_s : string
  end
end
