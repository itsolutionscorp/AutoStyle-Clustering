class Series

  attr_reader :numeric_string

  def initialize(numeric_string)
    @numeric_string = numeric_string
  end

  def slices(count)
    raise ArgumentError if count > numeric_string.length

    result = []
    numeric_string.chars.each_cons(count) do |i|
      result << i.map{|x| x.to_i}
    end

    return result
  end
end
