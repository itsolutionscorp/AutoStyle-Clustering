def compute(base, compare)
    diff = 0




    (0..(base.length - 1)).each do |index|
      if base[index] != compare[index]
        diff += 1
      end
    end
    diff
  end