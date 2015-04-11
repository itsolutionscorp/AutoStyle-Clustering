class Raindrops

  def self.drops 
   { 3 => "Pling",
     5 => "Plang",
     7 => "Plong"
   }
 end

  def self.convert(num)
    result = ''

    drops.each do |number, word|
      result << word if num % number == 0
    end
    result.empty? ? num.to_s : result
  end

end
