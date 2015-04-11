class Year
  def self.leap?(year)
    modulo = Modulo.new(year)
    modulo.dividable?(4) && (!modulo.dividable?(100) || modulo.dividable?(400))
  end
end

class Modulo
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def dividable?(by)
    number % by == 0
  end
end
