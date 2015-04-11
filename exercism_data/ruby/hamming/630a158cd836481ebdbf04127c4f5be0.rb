module Hamming
  def self.compute(fst_str, snd_str)
    fst_str.each_char.lazy.zip(snd_str.each_char.lazy)
      .take_while { |fst, snd| fst && snd }
      .select { |fst, snd| fst != snd }
      .count
  end
end
