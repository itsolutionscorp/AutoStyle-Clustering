class Raindrops
  def self.convert(number)
    new(number).get_conversion
  end

  attr_reader :number, :output

  def initialize(number)
    @number = number
    @output = ''
  end

  def get_conversion
    pling?
    plang?
    plong?
    number?
    output
  end

  private

  def pling?
    output << 'Pling' if number % 3 == 0
  end

  def plang?
    output << 'Plang' if number % 5 == 0
  end

  def plong?
    output << 'Plong' if number % 7 == 0
  end

  def number?
    output << number.to_s if output.empty?
  end
end
