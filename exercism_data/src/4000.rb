def compute(arg1,arg2)
    diffs = 0
    arr = arg1.split("").zip(arg2.split(""))
    arr.each do |stuff|
      if stuff[0] != stuff[1]
        diffs = diffs + 1
      end
    end
    return diffs
  end