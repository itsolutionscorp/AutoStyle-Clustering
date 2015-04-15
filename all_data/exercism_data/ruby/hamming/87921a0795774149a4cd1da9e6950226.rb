class Hamming
  def self.compute(first, second)
    first.each_char.with_index.count { |c, i| c != second[i] }

    # More verbose one-liners
    # first.each_char.map.with_index { |c, i| c == second[i] ? 0 : 1 }.reduce(&:+)
    # first.each_char.select.with_index { |c, i| c != second[i] }.length
  end
end
