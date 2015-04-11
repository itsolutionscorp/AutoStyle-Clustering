class Hamming

  def self.compute(origin_strand,other_strand)
    counts = 0
    long_strand,short_strand = self.order_strand(origin_strand,other_strand)
    long_strand_arr = long_strand.chars
    short_strand.chars.each_with_index do |x,i|
      counts +=  long_strand_arr[i] != x ? 1 : 0 
    end 
    counts
  end

  private
  def self.order_strand(strand1,strand2)
    strand1.length > strand2.length ? [ strand1,strand2 ] :  [ strand2,strand1 ]
  end

end
