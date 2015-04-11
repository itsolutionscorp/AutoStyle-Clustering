class Raindrops
  def self.rules
    @rules ||= {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong",
    }
  end

  def self.convert number
    sounds = rules.map { |factor, sound|
      sound if number % factor == 0
    }.join

    sounds.empty? ? number.to_s : sounds
  end
end
