class Series

  def initialize(str)
    @input_str = str
  end

  def slices(chunk)
    raise ArgumentError if num > @str.length
    @input_str.chars.map(&:to_i).each_cons(chunk).to_a
  end
end
