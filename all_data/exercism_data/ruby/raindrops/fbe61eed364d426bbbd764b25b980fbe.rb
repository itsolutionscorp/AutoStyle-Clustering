class Raindrops
  def self.convert(num)
    str = ""
    str += "Pling" if num % 3 == 0
    str += "Plang" if num % 5 == 0
    str += "Plong" if num % 7 == 0
    if str =~/Pling|Plang|Plong/
      str
    else 
      num.to_s
    end
  end
end
