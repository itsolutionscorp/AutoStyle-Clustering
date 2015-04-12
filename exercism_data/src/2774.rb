class Hamming
  def compute(arg1,arg2)
    if arg1 == arg2
      return 0
    end
    diffs = 0
    for pos in 0..arg1.length - 1
      if arg1[pos].chr != arg2[pos].chr
        diffs = diffs + 1
      end
    end
    return diffs
  end
end
