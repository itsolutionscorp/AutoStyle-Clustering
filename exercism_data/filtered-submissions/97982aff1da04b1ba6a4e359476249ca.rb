class Hamming
  def compute( strand_a, strand_b )
    enum_strand_a = strand_a.each_char
    enum_strand_b = strand_b.each_char
    hamming_distance = 0

    loop do
      e1 = enum_strand_a.next
      e2 = enum_strand_b.next

      hamming_distance += 1 unless e1 == e2
    end
    hamming_distance
  end
end
