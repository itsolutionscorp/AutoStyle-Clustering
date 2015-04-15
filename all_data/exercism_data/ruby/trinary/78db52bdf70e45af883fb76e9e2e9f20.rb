class Trinary
  private
  attr_accessor :trinary_value

  def initialize(val)
    self.trinary_value=val
  end

  def valid?
    trinary_value=~/^[012]+$/
  end

  public
  def to_decimal
    valid? ? trinary_value.chars.reduce(0){|sum,n| sum*3+n.to_i} : 0
  end
end
