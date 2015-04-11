class Raindrops

  def self.convert(integer)
    str = ""
    str += "Pling" if integer%3==0
    str += "Plang" if integer%5==0
    str += "Plong" if integer%7==0
    str += integer.to_s if str.length==0
    str
  end

end
