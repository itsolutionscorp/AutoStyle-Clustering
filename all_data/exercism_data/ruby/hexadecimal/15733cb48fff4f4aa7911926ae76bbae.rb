class Hexadecimal
  def initialize (str)
    @val = /[^0-9a-fA-F]/ =~ str ? 0 : str.to_i(16)
  end
  def to_decimal
    @val
  end
end
