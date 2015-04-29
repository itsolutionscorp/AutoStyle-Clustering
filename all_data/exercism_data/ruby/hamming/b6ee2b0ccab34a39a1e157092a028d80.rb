class DNA

  attr_reader :sequence
  def initialize sequence
    @sequence = sequence
  end

  def hamming_distance input_sequence
    @sequence.chars.zip(input_sequence.chars).count{ |chars_element|
      chars_element[0] != chars_element[1] unless chars_element.include?(nil)
    }
  end

end
