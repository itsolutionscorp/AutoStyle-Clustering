class Raindrops
  def self.convert(number)
    str = ""

    str << "Pling" if number % 3 == 0
    str << "Plang" if number % 5 == 0
    str << "Plong" if number % 7 == 0

    str.empty? ? number.to_s : str
  end
end
