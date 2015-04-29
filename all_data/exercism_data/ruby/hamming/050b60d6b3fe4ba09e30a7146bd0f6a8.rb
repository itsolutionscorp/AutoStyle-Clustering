module Hamming
  def self.compute(fst_str, snd_str)
    diffs = 0

    fst_str.chars.zip(snd_str.chars) do |fst, snd|
      break if fst.nil? || snd.nil?
      diffs += 1 if fst != snd
    end

    diffs
  end
end
