class Raindrops
  def self.convert(i)
    @int = i
    @result = ""

    if self.pling
      @result << "Pling"
    end
    
    if self.plang
      @result << "Plang"
    end

    if self.plong
      @result << "Plong"
    end
    
   if self.pling == false && self.plang == false && self.plong == false
     @result << @int.to_s
   end
    @result
  end
  

   def self.pling
    @int % 3 == 0
   end

   def self.plang
     @int % 5 == 0
   end

   def self.plong
     @int % 7 == 0
   end
end
