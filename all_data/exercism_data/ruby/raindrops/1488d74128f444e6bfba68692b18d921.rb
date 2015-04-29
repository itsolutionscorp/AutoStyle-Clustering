class Raindrops

  def self.convert(x)
    @x = x
    result = "" + pling + plang + plong
    return result if !result.empty?
    "#{x}"
  end

  def self.pling
   if @x%3 == 0
    "Pling"
   else
    ""
   end
  end

  def self.plang
   if @x%5 == 0
    "Plang"
   else
    ""
   end
  end

  def self.plong
   if @x%7 == 0
    "Plong"
   else
    ""
   end
  end

end
