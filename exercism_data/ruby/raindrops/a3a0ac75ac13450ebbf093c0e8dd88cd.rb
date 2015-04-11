class Raindrops

  NUMBER = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(number)
   
    val = NUMBER.select{|key, value| number % key == 0 }.values.join
    
    return number.to_s if val.empty?

     val

  end  

end
