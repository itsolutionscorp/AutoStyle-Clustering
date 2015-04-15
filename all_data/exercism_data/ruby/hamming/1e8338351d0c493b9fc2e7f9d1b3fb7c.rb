class Hamming

  def self.compute(first_strand, second_strand)
    splits(first_strand, second_strand)
    second_strand_size
    size_conditional
  end

  def self.splits(a, b)
    @fs = a.chars
    @ss = b.chars
  end

  def self.second_strand_size
    @fs_size = @fs.count
    @ss_size = @ss.count
    @ary_fs = @fs[0..@ss_size-1]
  end

  def self.size_conditional
    case
    when @fs_size <= @ss_size then less_equal
    else greater_than
    end
  end

  def self.less_equal
    counter = @fs.zip(@ss).map{|fs, ss| fs == ss}
    counter.count(false)
  end

  def self.greater_than
    counter = @ary_fs.zip(@ss).map{|fs, ss| fs == ss}
    counter.count(false)
  end
end
