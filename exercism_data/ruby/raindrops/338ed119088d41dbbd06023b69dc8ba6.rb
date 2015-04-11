class Raindrops
  def convert(number)
    raindrop_speak = ""
    raindrop_speak += "Pling" if number % 3 == 0
    raindrop_speak += "Plang" if number % 5 == 0
    raindrop_speak += "Plong" if number % 7 == 0
    raindrop_speak.empty? ? number.to_s : raindrop_speak
  end
end
