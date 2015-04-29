class Raindrops
  def self.convert(number)
      s = ""
      s << "Pling" if number % 3 == 0
      s << "Plang" if number % 5 == 0
      s << "Plong" if number % 7 == 0
      s.empty? ? number.to_s : s
    end
  end
