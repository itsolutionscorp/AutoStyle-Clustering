class Raindrops
  def self.convert(number)
    (sound = divisible?("", number)) == "" ? number.to_s : sound
  end

  def self.divisible?(sound, number)
    {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}.each do |key, value|
      sound << value if number % key == 0
    end
    sound
  end

end
