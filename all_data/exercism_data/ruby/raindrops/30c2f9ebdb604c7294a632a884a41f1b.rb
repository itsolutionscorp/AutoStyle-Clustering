class Integer  
  def has_prime_factor? n 
    (self % n).zero? 
  end 
end 

class Raindrops 
  def self.convert n
    pling, plang, plong = 
    [3,5,7].map do |prime|
      n.has_prime_factor? prime 
    end 
    str = 
"#{'Pling' if pling}"\
"#{'Plang' if plang}"\
"#{'Plong' if plong}"
    str.empty? ? n.to_s : str 
  end 
end
