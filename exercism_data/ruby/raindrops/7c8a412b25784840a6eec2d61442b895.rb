class Raindrops

  def self.convert(num)

    return num.to_s unless num%3==0 || num%5==0 || num%7==0

    output = ''

    if num%3 == 0
      output << "Pling"
    end
    if num%5 == 0
      output << "Plang"
    end
    if num%7 == 0
      output << "Plong"
    end

    return output

  end
end
