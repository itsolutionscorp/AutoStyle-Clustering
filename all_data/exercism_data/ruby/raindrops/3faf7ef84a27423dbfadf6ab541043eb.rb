class Raindrops

  RAIN_NOTES = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(drops)
    rain_song = rain_notes(drops)
    rain_song.empty? ? drops.to_s : rain_song.join
  end

  def self.rain_notes(drops)
    collect(drops).values
  end

  def self.collect(drops)
    RAIN_NOTES.select do |raindrop, sound|
      (drops % raindrop).zero?
    end
  end

  private_class_method :rain_notes, :collect
end
