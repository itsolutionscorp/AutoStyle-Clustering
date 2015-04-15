class Raindrops
  def self.convert number
    sounds = {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong",
    }.map {|factor, sound| sound if number % factor == 0 }.join

    sounds.empty? ? number.to_s : sounds
  end
end
