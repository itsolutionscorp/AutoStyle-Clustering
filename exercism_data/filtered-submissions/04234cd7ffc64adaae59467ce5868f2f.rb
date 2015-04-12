# Computes the Hamming distance between two strings representing DNA strands
class Hamming
  def compute(first, second)
    first.chars.select.with_index do |item, idx|
      second.chars[idx] && (item != second.chars[idx])
    end.size
  end
end
