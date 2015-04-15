class Raindrops
  def self.convert(number)
    #Define string
    string = ""
    if number%3==0 || number%5==0 || number%7==0
      #check for prime factor 3
      string += "Pling" if number%3==0
      #check for primer factor 5
      string += "Plang" if number%5==0
      #check for prime factor 7
      string += "Plong" if number%7==0
    else
      string = number.to_s
    end
    string
  end
end
