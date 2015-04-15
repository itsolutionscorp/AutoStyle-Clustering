class DNA

  attr_reader :sequence
  def initialize sequence
    @sequence = sequence
  end

  def hamming_distance input_sequence
    count = 0
    test_sequence(input_sequence).chars.each_with_index { |char, index|
      if char != input_sequence[index]
        count = count + 1
      end
    }
    count
  end

  private

  def test_sequence input_sequence
    min_index = [@sequence.length, input_sequence.length].min
    @sequence[0..min_index-1]
  end

end
