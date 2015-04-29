class Hexadecimal
  KEY = {
    'a' => 10,
    'b' => 11,
    'c' => 12,
    'd' => 13,
    'e' => 14,
    'f' => 15
  }
  def initialize(string)
    @string = string
  end

  attr_reader :string

  def to_decimal
    return 0 if string =~ /[g-z]/
    array_builder(string)
    .reverse
    .each_with_index
    .map{|x,i| [x * 16 ** i]}
    .inject(:+)
    .inject(:+)
  end

  private
  def array_builder(string)
    ans = []
    string.split('').map do |letter|
      if KEY.include? letter
        ans << KEY[letter]
      else
        ans << letter.to_i
      end
    end
  ans
  end
end
