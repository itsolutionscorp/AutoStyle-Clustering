class Raindrops
  def self.convert(number)
    results = []
    
    if number % 3 == 0
      results << "Pling"
    end

    if number % 5 == 0
      results << "Plang"
    end    
    
    if number % 7 == 0
      results << "Plong"
    end
    
    if results.length == 0
      return number.to_s
    else
      results.join
    end
  end
end
