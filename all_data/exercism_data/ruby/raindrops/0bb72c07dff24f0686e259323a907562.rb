class Raindrops
  DROPS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    result = ''
    DROPS.each { |key, value| result << value if number % key == 0 }
    result == '' ? number.to_s : result
  end
end
