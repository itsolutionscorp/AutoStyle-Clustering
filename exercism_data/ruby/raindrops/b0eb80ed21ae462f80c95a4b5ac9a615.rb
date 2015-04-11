class Raindrops
  def self.convert(number)
    sounds =
      {
        3 => 'Pling',
        5 => 'Plang',
        7 => 'Plong'
      }.select { |factor, sound| sound if (number % factor) == 0 }.values
    sounds.empty? ? number.to_s : sounds.join('')
  end
end
