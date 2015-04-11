class Series

  def initialize(str)
    @str = str
  end

  def slices(num)
    raise ArgumentError if num > @str.length
    result = []
    @str.chars.map(&:to_i).each_cons(num){|group| result << group}
    result
  end
end
