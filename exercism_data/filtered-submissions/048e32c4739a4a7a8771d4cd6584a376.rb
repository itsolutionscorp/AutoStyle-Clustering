class Hamming

  def compute(first_strand, second_strand)

    fs = first_strand.split(//)
    ss = second_strand.split(//)

    fs_size = fs.count
    ss_size = ss.count
    ary_fs = fs[0..ss_size-1]

    if fs_size <= ss_size
      counter = fs.zip(ss).map{|fs, ss| fs == ss}
      counter.count(false)
    else
       counter = ary_fs.zip(ss).map{|fs, ss| fs == ss}
       counter.count(false)
    end

  end

end
