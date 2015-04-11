class Hamming

  def self.compute(first_strand, second_strand)

    @fs = first_strand.split(//)
    @ss = second_strand.split(//)

    @fs_size = @fs.count
    @ss_size = @ss.count
    @ary_fs = @fs[0..@ss_size-1]

    size_conditional
  end

  def self.size_conditional
    case
    when @fs_size <= @ss_size then less_equal
    when @fs_size >  @ss_size then greater
    end
  end

  def self.less_equal
    counter = @fs.zip(@ss).map{|fs, ss| fs == ss}
    counter.count(false)
  end

  def self.greater
    counter = @ary_fs.zip(@ss).map{|fs, ss| fs == ss}
    counter.count(false)
  end

end
