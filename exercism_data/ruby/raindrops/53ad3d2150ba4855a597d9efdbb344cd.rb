class Raindrops
  SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    sounds = ''

    SOUNDS.each do |prime_factor, sound|
      sounds << sound if number % prime_factor == 0
    end

    sounds.empty? ? number.to_s : sounds
  end
end
