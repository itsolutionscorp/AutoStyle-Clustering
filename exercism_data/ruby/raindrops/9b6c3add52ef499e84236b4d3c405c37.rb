class Raindrops

  RAINDROP_MAPPING = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }
  def self.convert(number)
    output = RAINDROP_MAPPING.select { |prime, _| number % prime == 0 }.values
    output.empty? ? number.to_s : output.join
  end

end
