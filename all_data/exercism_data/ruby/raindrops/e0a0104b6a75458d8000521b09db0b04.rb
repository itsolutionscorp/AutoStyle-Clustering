class Raindrops

  def self.convert(number)
    r = Raindrops.new(number)
    r.convert
  end

  def initialize(number)
    @number = number
  end

  def convert
    to_s
  end

  def to_s
    if raindrops?
      raindrop_string 
    else
      @number.to_s
    end
  end

  def raindrop_string
    (pling? ? "Pling" : "") + 
    (plang? ? "Plang" : "") + 
    (plong? ? "Plong" : "")
  end

  def raindrops?
    pling? || plang? || plong?
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
