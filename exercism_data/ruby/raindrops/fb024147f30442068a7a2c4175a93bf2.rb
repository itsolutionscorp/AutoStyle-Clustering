class Raindrops

  @@conversions = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  
  def self.convert( number )
    factors = @@conversions.select { |divisor, sound| number % divisor == 0 }
    factors.empty? ? number.to_s : factors.values.join
  end

end
