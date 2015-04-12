class Hamming
  class << self
    def compute seq_a, seq_b
      seq_a.split('').zip(seq_b.split('')).select{ |base| base[0] != base[1] }.size
    end
  end
end
