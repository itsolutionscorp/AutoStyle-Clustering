class Raindrops
  class << self
    def convert(input)
      new(input).convert
    end
  end

  def initialize(input)
    @input = input
  end

  def convert
    return "#@input" if !pling? && !plang? && !plong?
    output = ''
    output << 'Pling' if pling?
    output << 'Plang' if plang?
    output << 'Plong' if plong?
    output
  end

  private
  def pling?
    @input % 3 == 0
  end

  def plang?
    @input % 5 == 0
  end

  def plong?
    @input % 7 == 0
  end
end
