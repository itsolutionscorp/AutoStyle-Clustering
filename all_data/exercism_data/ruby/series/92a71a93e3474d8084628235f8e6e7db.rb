class Series
  private
  attr_reader :string
  def initialize(string)
    @string=string
  end

  public
  def slices(num)
    raise ArgumentError if num > string.length
    (string.length+1-num).times.map {|x| string.chars[x...x+num].map(&:to_i)}
  end
end
