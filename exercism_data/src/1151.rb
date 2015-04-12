def compute first, other
    pairs = first.each_char.zip(other.each_char)

    pairs.take(other.size).count do |a, b|
      a != b
    end
  end