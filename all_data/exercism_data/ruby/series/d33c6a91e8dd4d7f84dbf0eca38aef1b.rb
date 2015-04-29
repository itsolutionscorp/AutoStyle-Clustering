class Series

  def initialize str
    @str = str.chars.map! { |i| i.to_i}
  end

  def slices n
    raise ArgumentError.new "fooey!" if n > @str.length
    (0..(@str.length-n)).each_with_object([]) do |start, memo|
      memo << @str.slice(start,n)
    end
  end
end
