class Hamming
  def self.compute(first, second)
    raise ArgumentError, "string sizes must be equal" if first.size != second.size

    first.chars.zip(second.chars).reduce(0) do |count, (first_char, second_char)|
      count + compare_chars_value(first_char, second_char)
    end
  end

  def self.compare_chars_value(first_char, second_char)
    first_char == second_char ? 0 : 1
  end
end
