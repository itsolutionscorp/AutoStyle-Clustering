class Series
  def initialize str
    @str = str
    @str_length = str.length
  end

  def slices length
    raise ::ArgumentError.new("Slice length must not be longer than the original string. Got #{length}. Expected length <= #{@str_length}") if length > @str_length
    result = []
    (0..@str_length-length).each do |i|
      result << @str[(i..i+length-1)].split("").map(&:to_i)
    end
    result
  end
end
