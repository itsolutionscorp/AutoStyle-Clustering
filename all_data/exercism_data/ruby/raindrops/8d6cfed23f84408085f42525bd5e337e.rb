class Raindrops
  MAPPING = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    result = MAPPING.map{ |factor, word| number % factor == 0 ? word : "" }.join
    result.empty? ? number.to_s : result
  end
end
