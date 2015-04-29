class Raindrops
  def self.convert(number)
    raindrops = ''
    raindrops << 'Pling' if number%3 == 0
    raindrops << 'Plang' if number%5 == 0
    raindrops << 'Plong' if number%7 == 0
    raindrops << number.to_s if raindrops.empty?
    raindrops
  end
end
