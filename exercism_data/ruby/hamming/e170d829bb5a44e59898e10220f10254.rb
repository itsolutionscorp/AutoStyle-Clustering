class Hamming

  def self.compute(strand, other)
    other_chars = other.each_char

    differences =
      strand.each_char.map do |char|
        begin
          char == other_chars.next ? 0 : 1
        rescue StopIteration
          0
        end
      end

    differences.reduce :+
  end

end
