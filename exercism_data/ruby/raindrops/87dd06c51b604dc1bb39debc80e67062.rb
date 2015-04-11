class Raindrops

  RAIN_NOTES = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(drops)
    rain_song = rain_notes(drops)
    rain_song.empty? ? "#{drops}" : rain_song.join
  end

  private

  def self.rain_notes(drops)
    RAIN_NOTES.collect do |raindrop, sound|
      sound if (drops%raindrop).zero?
    end.compact
  end
end
