class Raindrops

  def self.convert(str1,message = "")
    message << "Pling" if str1 % 3 == 0
    message << "Plang" if str1 % 5 == 0
    message << "Plong" if str1 % 7 == 0

    message != "" ? message : str1.to_s

  end

end
