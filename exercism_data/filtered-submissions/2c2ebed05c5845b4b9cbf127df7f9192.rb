module Hamming
  def compute(*strings)
    length = Array(strings).map(&:to_s).map(&:size).min
    sequences = strings.map { |s| s.chars.take(length) }
    sequences.transpose.reject(&:uniq!).size
  end
end
