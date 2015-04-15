class Hamming
  class << self
    def compute(a, b)
      a.chars.map.with_index { |c, i| c && b[i] && (c != b[i]) }.count(true)
    end
  end
end
