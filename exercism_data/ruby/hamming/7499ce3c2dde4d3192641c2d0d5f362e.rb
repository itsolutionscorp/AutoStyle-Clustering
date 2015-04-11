class Hamming

  def self.compute(first_strand, second_strand)
    to_array(first_strand, second_strand)
    conditional
  end

  def self.to_array(a.chars, b.chars)
    @a = a
    @b = b
  end

  def self.conditional
    @a.count <= @b.count ? @n = @a : @n = @a[0..@b.count-1]
    counter = @n.zip(@b).map{|fs, ss| fs == ss}
    counter.count(false)
  end

end
