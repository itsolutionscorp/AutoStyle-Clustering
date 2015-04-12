class Hamming
  def compute(first, second)
    distance = 0

    first.each_char.with_index do |c, i|
      distance += 1 if c != second[i]
    end

    distance

    # One-liner
    # first.chars.map.with_index { |c, i| c == second[i] ? 0 : 1 }.reduce(&:+)
  end
end
