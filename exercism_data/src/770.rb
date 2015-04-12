def compute(string, other_string)
    string = string.scan(/\w/)
    other_string = other_string.scan(/\w/)

    if string.length > other_string.length
      string = string.slice(0, other_string.length)
    elsif other_string.length > string.length
      other_string = other_string.slice(0, string.length)
    end

    distance = string.zip(other_string).map do |a, b|
      a != b ? 1 : 0
    end.inject(:+)

    distance
  end