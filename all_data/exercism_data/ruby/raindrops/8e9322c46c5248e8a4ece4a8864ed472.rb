class Raindrops

  FACTOR_TO_TEXT = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(number)
    converted = ""
    FACTOR_TO_TEXT.each_pair do |factor, text|
      converted << text if number % factor == 0
    end

    return "#{number}" if converted.empty?
    converted
  end

end
