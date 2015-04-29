class Raindrops

  def self.convert(drops)
    @rain_notes = {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
    @rain_song = []
    @numbers = []

    @rain_notes.each do |r, s|
      if (drops%r).zero?
        @rain_song << s
      else
        @numbers << drops
      end
    end
    if @rain_song.empty?
      "#{@numbers.first}"
    else
      @rain_song.join
    end
  end

end
