class Raindrops
  def self.convert(number)
    raindrop_speak = ""

    raindrop_speak += "Pling" if number % 3 == 0
    raindrop_speak += "Plang" if number % 5 == 0
    raindrop_speak += "Plong" if number % 7 == 0

    return number.to_s if raindrop_speak.empty?
    raindrop_speak
  end
end
