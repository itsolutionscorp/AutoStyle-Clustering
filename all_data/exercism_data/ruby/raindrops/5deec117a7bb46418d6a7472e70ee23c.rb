class Raindrops

  CONVERSIONS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(n)
    conversions_to_use = CONVERSIONS.select { |k,v| n % k == 0 }
    plng_str = conversions_to_use.values.join
    plng_str.empty? ? n.to_s : plng_str
  end
end
