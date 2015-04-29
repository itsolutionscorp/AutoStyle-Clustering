class Hamming
  class << self
    def compute(strand1, strand2)
      length = least_length(strand1, strand2)
      length.times.count { |i| strand1[i] != strand2[i] }
    end

    private

    def least_length(s1, s2)
      s1.length <= s2.length ? s1.length : s2.length
    end
  end
end
