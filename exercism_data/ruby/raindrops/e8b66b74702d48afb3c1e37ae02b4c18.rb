class Raindrops
  DECODER = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(drops)
    rain_noise = Raindrops::DECODER.map { |count, sound | drops % count == 0 ?  sound : "" }.join
    rain_noise == "" ? drops.to_s : rain_noise
  end

end
