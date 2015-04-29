class Raindrops
 
 def self.convert(num)
   a = ""
   a += "Pling" if is_divisible_by?(num, 3)
   a += "Plang" if is_divisible_by?(num, 5)
   a += "Plong" if is_divisible_by?(num, 7)

   a += num.to_s if a == ""

 end

  def self.is_divisible_by?(num, diviser)
    num%diviser ==0
   end

end
