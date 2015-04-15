class Raindrops
  RaindropLanguage = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(x)
    raindrops_translation = translate_raindrops(x)
    if raindrops_translation.empty?
      x.to_s
    else
      raindrops_translation
    end
  end

  def self.translate_raindrops(x)
    "".tap do |output|
      RaindropLanguage.each do |number, word|
        output << word if divisible_by(x, number)
      end
    end
  end

  def self.divisible_by(x, number)
    x % number == 0
  end
end
