class Raindrops

  def self.convert(number)
    array = []
    string = ""
    1.upto(number) do |n|
      if number%n == 0
        array << n
      end
    end
    if array.include?(3)
      string += "Pling"
    end
    if array.include?(5)
      string += "Plang"
    end
    if array.include?(7)
      string += "Plong"
    end
    return string.empty? ? "#{number}" : string
  end

end
