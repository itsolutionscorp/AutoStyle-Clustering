##Raindrops
##John Youngblood
##1/26/2015
class Raindrops
  def self.convert(num)
    "".tap do |rain|
      rain << "Pling" if num % 3 == 0
      rain << "Plang" if num % 5 == 0
      rain << "Plong" if num % 7 == 0
      rain << num.to_s if rain.empty?
    end
  end
end
