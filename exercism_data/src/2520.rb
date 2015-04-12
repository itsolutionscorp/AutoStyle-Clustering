class Hamming
  class << self
    def compute(a, b)
      hamming_diff = 0
      a, b = a.split(""), b.split("")
      a.length.times do |i|
        break if a[i].nil? || b[i].nil?
        hamming_diff += 1 unless a[i] == b[i]
      end
      hamming_diff
    end
  end
end
