class Hamming
  class << self
    def compute(first, second)
      pairs = first.chars.zip(second.chars).take_while { |fst,snd| fst && snd }
      pairs.reduce(0) { |memo, (fst,snd)| memo += 1 if fst != snd; memo }
    end
  end
end
