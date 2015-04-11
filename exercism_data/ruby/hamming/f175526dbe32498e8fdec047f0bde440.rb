class Hamming
  def self.compute(first_sequence, second_sequence)
    @hamming_array = []
    @first_sequence_array = first_sequence.split(//)
    @second_sequence_array = second_sequence.split(//)

    raise UnEqualSequencesException if @first_sequence_array.length != @second_sequence_array.length
    @first_sequence_array.zip(@second_sequence_array) do |first, second|
      @hamming_array << HammingPair.new(first,second)
    end

    @hamming_array.map(&:match_value).inject(&:+)
  end
end

class HammingPair
  def initialize(first, second)
    @first = first
    @second = second
  end
  
  def match_value
    match? ? 0 : 1
  end
  private
  def match?
    @first == @second
  end
end
