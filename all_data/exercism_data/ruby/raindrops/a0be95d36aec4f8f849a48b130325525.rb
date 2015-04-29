class Raindrops
  def self.convert(x)
    output = ''
    
    if x % 3 == 0
      output << 'Pling'
    end
    
    if x % 5 == 0
      output << 'Plang'
    end
    
    if x % 7 == 0
      output << 'Plong'
    end
    
    if output == ''
      output = x.to_s
    end
    
    output
  end
end
