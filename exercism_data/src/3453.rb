class Hamming
  def compute(first, second)
    first.chars.zip(second.chars).count do |first_char, second_char|
      first_char != second_char && !first_char.nil? && !second_char.nil?
    end
  end
end
