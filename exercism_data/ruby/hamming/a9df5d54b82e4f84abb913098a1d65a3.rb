class Hamming

  def self.compute(strand1,strand2)
    @st1 = strand1.chars.to_enum
    @st2 = strand2.chars.to_enum
    a = 0
      loop do
        unless @st1.next == @st2.next
        a += 1
        end
      end
    a
  end
end
