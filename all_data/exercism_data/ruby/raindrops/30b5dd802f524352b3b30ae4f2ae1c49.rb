class Raindrops

  RAIN_NOTES = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(drops)
    rain_song = RAIN_NOTES.collect do |raindrop, sound|
      sound if (drops%raindrop).zero?
    end.compact

    return "#{drops}" if rain_song.empty?
    return rain_song.join
  end
end
