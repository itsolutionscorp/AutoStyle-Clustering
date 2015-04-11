class Raindrops
  def self.convert(number)
    raindrop_notation = mappings.collect do |factor, translation|
      translation if number % factor == 0
    end.join

    raindrop_notation.empty? ? number.to_s : raindrop_notation
  end

  private
  def self.mappings
    {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
  end
end
