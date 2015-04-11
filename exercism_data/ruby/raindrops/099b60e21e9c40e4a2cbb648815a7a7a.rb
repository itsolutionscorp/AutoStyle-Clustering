class Raindrops
  def initialize(number)
    @number = number
  end

  def self.convert(number)
    Raindrops.new(number).drops
  end


  def drops
    result = pling + plang + plong
    if result.empty? then
      @number.to_s
    else
      result
    end
  end

  def pling
    ((@number % 3) == 0 ? "Pling" : "")
  end

  def plang
    ((@number % 5) == 0 ? "Plang" : "")
  end

  def plong
    ((@number % 7) == 0 ? "Plong" : "")
  end


end
