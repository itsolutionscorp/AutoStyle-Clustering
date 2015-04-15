class Hamming
  def self.compute(a, b)
    return 0 if a == b

    is_error = ->((x, y)) { y && x != y }

    pairs      = a.chars.zip(b.chars)
    num_errors = pairs.count(&is_error)

    num_errors
  end
end
