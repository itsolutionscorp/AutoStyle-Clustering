class Raindrops
  def self.convert(num)
    final_string = pairs.keys.each_with_object("") do |element, result|
      result << pairs[element] if num % element == 0
    end

    if final_string == ""
      num.to_s
    else
      final_string
    end
  end

  def self.pairs
    pairs = {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
  end
end
