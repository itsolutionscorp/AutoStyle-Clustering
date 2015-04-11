class Raindrops

  RAIN_SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert (number)
    output = ""

    RAIN_SOUNDS.each_key do |factor|
      if number % factor == 0
        output.concat(RAIN_SOUNDS[factor])
      end
    end

    output.empty? ? number.to_s : output
  end

end
