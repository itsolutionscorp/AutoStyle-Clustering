class Hamming
  def Hamming.compute(first_sequence, second_sequence)
    if first_sequence.length != second_sequence.length
      raise "required same length: #{first_sequence.length} != #{second_sequence.length}"
    end
    first_sequence.chars.zip(second_sequence.chars).count do |first_character, second_character|
      first_character != second_character
    end
  end
end
