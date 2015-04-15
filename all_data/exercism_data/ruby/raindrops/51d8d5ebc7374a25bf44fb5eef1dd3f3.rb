class Raindrops
  def self.convert(number)
    new(number).to_s
  end

  attr_reader :number
  def initialize(number)
    @number = number
  end

  def to_s
    buffer = [pling, plang, plong]
    buffer.compact!
    buffer << number if buffer.empty?
    buffer.join
  end

  def pling
    'Pling' if number % 3 == 0
  end

  def plang
    'Plang' if number % 5 == 0
  end

  def plong
    'Plong' if number % 7 == 0
  end
end
