class Raindrops
  require 'prime'

  def self.convert(number)
    out = ""
    out << "Pling" if number % 3 == 0
    out << "Plang" if number % 5 == 0
    out << "Plong" if number % 7 == 0

    out.empty? ? number.to_s : out
  end
end
