class Raindrops
  
  CONVERTER = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
  
  def self.convert(number)
    
    converted = CONVERTER.select{|factor| number % factor == 0}
    converted.empty? ? number.to_s : converted.values.join

  end

end
