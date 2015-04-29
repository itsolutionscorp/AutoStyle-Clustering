class Raindrops

  DROPS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    string(number).empty? ? number.to_s : string(number)
  end

  def self.string(number)
    DROPS.select { |key| number % key == 0 }.values.join
  end

end
