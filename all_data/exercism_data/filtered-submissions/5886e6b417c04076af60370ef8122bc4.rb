def compute(seq1, seq2)
    [].tap { |arr|
      seq1.bytes.zip(seq2.bytes) { |a, b|
        arr << (a ^ b) if a && b
      }
    }.count(&:nonzero?)
  end