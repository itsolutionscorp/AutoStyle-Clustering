class Raindrops 

  def self.convert(num)
    val = '' 

    val += 'Pling' if num % 3 == 0
    val += 'Plang' if num % 5 == 0
    val += 'Plong' if num % 7 == 0
    
    val = num.to_s if val.empty?

    val
  end
end
