class Raindrops

  def self.convert(number)
    new(number).convert
  end

  attr_reader :number

  def initialize(number)
    @number = number
  end

  def convert
    str = ""
    str << "Pling" if pling?
    str << "Plang" if plang?
    str << "Plong" if plong?

    str.empty? ? number.to_s : str
  end

  def pling?
    number % 3 == 0
  end

  def plang?
    number % 5 == 0
  end

  def plong?
    number % 7 == 0
  end

end
