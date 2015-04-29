class Raindrops
  
  # def self.convert(number)
  #   ret = ""
  #   ret << "Pling" if number % 3 == 0
  #   ret << "Plang" if number % 5 == 0
  #   ret << "Plong" if number % 7 == 0
  #   ret << number.to_s if ret == ""
  #   ret
  # end
  
  def self.convert(number)
    sounds ={ 3=> "Pling", 5=> "Plang", 7=> "Plong"}
    ret = sounds.each_key.to_a.reduce("") {|a,n|  number % n == 0 ? a << sounds[n]: a } 
    ret.empty? ? number.to_s : ret 
  end
    
end  
