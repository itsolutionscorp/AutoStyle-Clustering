class Raindrops
  PRIMES = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }.freeze

  def self.convert(num)
    sounds = ''
    PRIMES.each do |prime, sound|
      if num % prime == 0
        sounds << sound
      end
    end

    sounds.empty? ? num.to_s : sounds
  end

end
