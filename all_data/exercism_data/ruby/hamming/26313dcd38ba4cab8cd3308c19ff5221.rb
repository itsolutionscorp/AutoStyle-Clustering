class Hamming
  class << self
    def apply sequence = [], method, strand, other_strand
      strand.chars.each_with_index do |symbol, index|
        sequence.send(method, symbol + index.to_s) if index < other_strand.size
      end

      sequence
    end

    def compute strand, other_strand
      apply(apply(:push, strand, other_strand), :delete, other_strand, strand).size
    end
  end
end
