def compute(str1, str2)
    short, long = [str1, str2].sort_by(&:length)
    short.chars.zip(long.chars).inject(0) { |diff, c| c[0]==c[1] ? diff : diff+1 }
  end