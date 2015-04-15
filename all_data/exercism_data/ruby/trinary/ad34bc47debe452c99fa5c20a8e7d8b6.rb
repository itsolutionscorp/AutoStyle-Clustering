class Trinary
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def to_decimal
    return 0 unless string =~ /\A[0-2]+\Z/
    string.chars.each_with_index.inject(0) do |sum, (char, exp)|
      sum * 3 + char.to_i
    end
  end
end
