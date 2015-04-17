class Raindrops
  
  def self.convert(number)
    
    converted = ''
    
    converted << 'Pling' if divisible_by(number, 3)
    converted << 'Plang' if divisible_by(number, 5)
    converted << 'Plong' if divisible_by(number, 7)
    
    converted.empty? ? number.to_s : converted

  end
  
  private
  
  def self.divisible_by(number, factor)
    
    number % factor == 0
    
  end

end