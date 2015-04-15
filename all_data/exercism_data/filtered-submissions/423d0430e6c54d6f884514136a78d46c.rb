def compute(arg1, arg2)
    return 0 if arg1 == arg2

    hd = 0
    for idx in 0..arg1.length - 1
      if arg1[idx] != arg2[idx]
        hd += 1
      end
    end

    hd
  end