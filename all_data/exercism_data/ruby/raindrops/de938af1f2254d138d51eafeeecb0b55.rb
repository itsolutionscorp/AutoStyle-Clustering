class Raindrops
  FACTORS = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
  }

  def self.convert (num)
    str = FACTORS.each.collect { |key, value|
      value if num % key == 0
    }.join
    str.empty? ? num.to_s : str
  end
end
