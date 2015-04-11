class Raindrops

  def self.convert(num)
    output = ""
    if num % 3 == 0 then
      output += "Pling"
    end
    if num % 5 == 0  then
      output += "Plang"
    end
    if num % 7 == 0  then
      output += "Plong"
    end

    return num.to_s if output == ""
    output
  end

end
