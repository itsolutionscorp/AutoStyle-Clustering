def compute(string_a, string_b)
    array_a = string_a.chars
    array_b = string_b.chars
    zipped = array_a.zip(array_b)

    zipped.count do |a,b|
      a != b
    end
  end