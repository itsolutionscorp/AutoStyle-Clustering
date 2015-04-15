class Raindrops

  DROPS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    string = DROPS.keys.map { |i| DROPS[i] if number % i == 0 }.join
    string.empty? ? number.to_s : string
  end

end
