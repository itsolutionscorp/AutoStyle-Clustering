def compute(first, other)
      return compute(other, first) if first.length > other.length

      first.length.times.map do |index|
        next 0 if first[index] == other[index]
        1
      end.reduce(&:+)
    end