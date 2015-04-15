class Raindrops
  def self.convert(number)
    collection = ""

    collection << "Pling" if number % 3 == 0
    collection += "Plang" if number % 5 == 0
    collection += "Plong" if number % 7 == 0
    collection += number.to_s if number % 3 != 0 && number % 5 != 0 && number % 7 != 0
    collection
  end
end
