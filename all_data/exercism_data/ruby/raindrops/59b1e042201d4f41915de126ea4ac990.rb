class Raindrops
  CONVERSIONS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
  def self.convert(number)
    str = CONVERSIONS.collect do |factor, text|
      text if number % factor == 0
    end.compact.join
    str.size == 0 ? number.to_s : str
  end
end
