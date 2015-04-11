class Raindrops

  def self.convert(number)
    r = Raindrops.new(number)
    r.convert
  end

  def initialize(number)
    @number = number
  end

  def convert
    returnme = ""
    returnme += "Pling" if pling?
    returnme += "Plang" if plang?
    returnme += "Plong" if plong?
    return @number.to_s if returnme == ""
    return returnme 
  end

  def pling?
    @number % 3 == 0
  end

  def plang?
    @number % 5 == 0
  end

  def plong?
    @number % 7 == 0
  end

end
