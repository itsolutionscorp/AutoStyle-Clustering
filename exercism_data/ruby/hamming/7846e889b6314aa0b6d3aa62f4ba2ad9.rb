class Hamming
  class << self
    def compute seq_a, seq_b
      seq_a.chars.zip(seq_b.chars).select{ |base| base[0] != base[1] }.size
    end
  end
end
