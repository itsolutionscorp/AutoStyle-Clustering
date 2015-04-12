def compute(first_strand, second_strand)
    @a = first_strand.chars
    @b = second_strand.chars
    @a.count <= @b.count ? @n = @a : @n = @a[0..@b.count-1]
    counter = @n.zip(@b).map{|fs, ss| fs == ss}
    counter.count(false)
  end