class Hamming
  class << self
    def compute(first, second)
      first.chars.zip(second.chars).
        take_while { |fst,snd| fst && snd }.
        count { |fst,snd| fst != snd}
    end
  end
end
