class Raindrops
  
  def self.convert(number)
    ret = ""
    ret << "Pling" if number % 3 == 0
    ret << "Plang" if number % 5 == 0
    ret << "Plong" if number % 7 == 0
    ret << number.to_s if ret == ""
    ret
  end  
  
end  
