class Binary
  private
  attr_accessor :string
  def initialize(string)
    self.string=string
  end

  def valid?
    string.match(/^[01]+$/)
  end

  public
  def to_decimal
    return 0 unless valid?
    string.chars.inject(0){|sum,num| sum*2+num.to_i}
  end
end
