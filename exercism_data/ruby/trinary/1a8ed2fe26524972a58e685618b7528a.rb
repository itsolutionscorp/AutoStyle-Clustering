class Trinary
  def initialize string
    @trinary = (/^[012]+$/ =~ string).nil? ? '0' : string
  end

  def to_decimal
    @trinary.chars.reverse.each_with_index.reduce(0) do |total, (char, i)|
      total + Integer(char) * 3**i
    end
  end
end
