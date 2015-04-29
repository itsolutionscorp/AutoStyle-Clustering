class Raindrops
  @drops = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    result = ''
    @drops.each { |key, value| result << value if number % key == 0 }
    result.empty? ? number.to_s : result
  end
end
