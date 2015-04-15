class Raindrops
  RAIN_DROPS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert number
    rain = ""
    RAIN_DROPS.each { |key, value| rain << value if number % key == 0 }
    return rain == "" ? number.to_s : rain
  end
end
