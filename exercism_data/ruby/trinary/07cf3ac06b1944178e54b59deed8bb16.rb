class Trinary
  def initialize(convertable_string)
    @convertable_string = convertable_string
  end

  def to_decimal
    Integer(@convertable_string, 3)
  rescue
    0
  end
end
