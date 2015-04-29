class Raindrops
  DECODER = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(drops)
    rain_noise = DECODER.collect { |count, sound| sound if drops % count == 0 }.join
    rain_noise == "" ? drops.to_s : rain_noise
  end

end
