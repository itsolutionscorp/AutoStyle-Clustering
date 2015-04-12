class Hamming
  def compute(first_range, second_range)
    first_range.chars.each_with_index.count do |_char, index|
      first_range[index] != second_range[index]
    end
  end
end
