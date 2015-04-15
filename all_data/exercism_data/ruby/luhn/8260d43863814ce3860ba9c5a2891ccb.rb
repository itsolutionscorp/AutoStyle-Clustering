class Luhn

  def initialize(number)
    @digits = number.to_s.chars
  end

  def addends
    @digits
      .reverse
      .map
      .with_index { |e, i| e.to_i * (i.odd? ? 2 : 1) }
      .map { |e| e > 9 ? e - 9 : e }
      .reverse
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    (checksum % 10).zero?
  end

  def self.create nb
    10.times
      .lazy
      .map { |i| nb.to_s + i.to_s }
      .find { |e| Luhn.new(e).valid? }
      .to_i
  end

end
