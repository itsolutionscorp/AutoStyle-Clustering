##Raindrops
##John Youngblood
##1/26/2015
class Raindrops
  def self.convert(num)
    rain = ""
    rain << "Pling" if num % 3 == 0; rain << "Plang" if num % 5 == 0; rain << "Plong" if num % 7 == 0
    if rain == ""
      num.to_s
    else
      rain
    end
  end
end
