class Raindrops
  def self.convert(number)
    "".tap do |string|
      string << "Pling" if number % 3 == 0
      string << "Plang" if number % 5 == 0
      string << "Plong" if number % 7 == 0

      string << number.to_s if string == ""
    end
  end
end
