class Series

  def initialize(string)
    @string = string
  end

  def slices(size)
    raise ArgumentError if size > @string.length
    @string.chars.map(&:to_i).each_cons(size).to_a
  end
  
end
