class Raindrops
  class Drop < Struct.new(:number)
    def to_s
      converted = ""
      converted << "Pling" if divisible_by? 3
      converted << "Plang" if divisible_by? 5
      converted << "Plong" if divisible_by? 7
      converted.empty? ? number.to_s : converted
    end
    
    private
    def divisible_by? div
      number%div==0
    end
  end
  
  def convert number
    Drop.new(number).to_s
  end
end
