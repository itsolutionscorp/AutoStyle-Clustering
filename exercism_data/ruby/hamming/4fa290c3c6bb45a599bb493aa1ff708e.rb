module Hamming
  def self.compute(fst_str, snd_str)
    diffs = 0

    Util.each_of(fst_str.chars, snd_str.chars) do |fst, snd|
      diffs += 1 if fst != snd
    end

    diffs
  end

  module Util
    def self.each_of(*arrays)
      max_length = arrays.map(&:length).min

      return enum_for(:each_of) { max_length } unless block_given?

      (0...max_length).each do |i|
        yield *arrays.map { |arr| arr[i] }
      end
    end
  end
end
