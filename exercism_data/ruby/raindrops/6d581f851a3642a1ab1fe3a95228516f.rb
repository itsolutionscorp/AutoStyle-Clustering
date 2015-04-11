class Raindrops

  def self.convert(x)
    
    str = ''
    str += 'Pling' if x % 3 == 0
    str += 'Plang' if x % 5 == 0
    str += 'Plong' if x % 7 == 0

    str = x.to_s if str.empty?
    str
  end
 
end
