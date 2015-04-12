class Hamming
  class << self
    def compute (strand_a, strand_b, begin_distance:0)
      min_length = [strand_a.length, strand_b.length].min
      min_length.times do |position|
        begin_distance = begin_distance + 1 unless strand_a[position] == strand_b[position]
      end
      return begin_distance
    end
  end
end
