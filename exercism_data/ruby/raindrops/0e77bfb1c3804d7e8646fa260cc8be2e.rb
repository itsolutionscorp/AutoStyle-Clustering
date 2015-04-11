class Raindrops
  DROPS = { 3 => 'i', 5 => 'a', 7 => 'o' }

  def self.convert(number)
    drops = DROPS.reduce('') do |str, (prime, letter)|
      str << "Pl#{letter}ng" if (number % prime == 0); str
    end

    drops.empty? ? number.to_s : drops
  end
end
