class Raindrops
  def self.convert(a)
    conversions = {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
    output = ""
    conversions.each do |factor, conversion|
      output << conversion if a % factor == 0
    end
    output.empty? ? a.to_s : output
  end
end
