class Binary
  attr_accessor :string
  def initialize(string)
    self.string=string
  end

  def is_valid?
    string.match(/^[01]+$/)
  end

  def to_decimal
    return 0 unless is_valid?
    string.chars.inject(0){|sum,num| sum*2+num.to_i}
  end
end
