class Raindrops

  def self.convert(number)
    self.evaluator(number) == "" ? number.to_s : self.evaluator(number)
  end

  def self.evaluator(number)
    word ||= "#{pling(number)}#{plang(number)}#{plong(number)}"
  end

  def self.pling(number)
    "Pling" if number%3 == 0
  end

  def self.plang(number)
    "Plang" if number%5 == 0
  end

  def self.plong(number)
    "Plong" if number%7 == 0
  end

end
