def compute(*strings)
    strings = Array(strings).map(&:to_s)
    length = strings.map(&:size).min
    sequences = strings.map { |s| s.chars.take(length) }
    sequences.transpose.reject(&:uniq!).size
  end
end