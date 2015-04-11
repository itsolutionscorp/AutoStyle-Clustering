class Raindrops
  def self.convert(number)
    new(number).convert
  end

  def initialize(number)
     @number = number
  end

  attr_reader :number

  def convert
    unless pling? || plang? || plong?
      return number.to_s
    end

    output = '' 
    output << 'Pling' if  pling?
    output << 'Plang' if  plang?
    output << 'Plong' if  plong?
    output
  end

  private
  def pling?
    (number % 3) == 0
  end

  def plang?
    (number % 5) == 0
  end

  def plong?
    (number % 7) == 0
  end
end
