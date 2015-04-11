class Raindrops
  RAINDROP_SPEAK = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
  }

  def self.convert(number)
    conversion = ''
    RAINDROP_SPEAK.each do |factor, translation|
      conversion << translation if number % factor == 0
    end

    # Return original number if no translation(s) made
    conversion.empty? ? number.to_s : conversion
  end

end
