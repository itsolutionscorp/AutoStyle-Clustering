class Series
  def initialize(string)
    @string = string
  end

  attr_accessor :string

  def slices(num)
    ans = []
    digits = string.split('').map(&:to_i)
    if num > string.length
      raise ArgumentError
    else
      combos = (0..digits.length).to_a.combination(2).map{|i,j| digits[i...j]}
      combos.each {|x| ans << x if x.length == num}
    end
  ans
  end
end
