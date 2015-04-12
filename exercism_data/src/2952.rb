class Hamming
  def compute(strand_a, strand_b)
    short, long = [strand_a, strand_b]
                  .map { |s| s.split('') }
                  .sort{ |a , b| a.size <=> b.size }
    short.zip(long)
         .count { |m, n| m != n }
  end
end