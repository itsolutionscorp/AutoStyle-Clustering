class Raindrops
  def self.convert(number)
    raindrop_string = convert_to_raindrop_string(number)
    return raindrop_string unless raindrop_string.empty?
    number.to_s
  end

  private

  def self.convert_to_raindrop_string(number)
    return_value = FACTOR_LOOKUP.map do |factor_key, value|
      number % factor_key == 0 ? value : ""
    end
    return_value.join
  end

  FACTOR_LOOKUP = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }
end
