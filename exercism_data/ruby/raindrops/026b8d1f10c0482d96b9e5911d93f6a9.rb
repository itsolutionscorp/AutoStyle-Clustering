class Raindrops

  def self.convert(n)
     if n % 105 == 0 
      return  "PlingPlangPlong"
     elsif n % 35 ==  0 
       return "PlangPlong"
     elsif n % 21 == 0
      return "PlingPlong"
     elsif n % 15 == 0
       return "PlingPlang"
     elsif n % 7  == 0
       return "Plong"
     elsif n % 3  == 0
       return "Pling"
     elsif n % 5  == 0
      return  "Plang"
     else
       return n.to_s
   end
end
end
