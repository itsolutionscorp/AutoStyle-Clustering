class Hamming
  def compute(one, other)
    strings_as_chars = [one, other].map { |string| string.chars.to_a }
    min_length = strings_as_chars.map(&:length).min
    stripped_chars = strings_as_chars.map { |chars| chars.first(min_length) }
    char_pairs = stripped_chars.transpose
    char_pairs.map { |one_char, other_char| one_char == other_char }.count(false)
  end
end
