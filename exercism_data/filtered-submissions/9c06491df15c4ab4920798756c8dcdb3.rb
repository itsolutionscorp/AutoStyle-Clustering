def compute(x,y)
    failures = 0
    success = 0
    array_1 = x.split(//)
    array_2 = y.split(//)
      (0..(array_1.size - 1)).each do |item|
        if (array_1[item] == array_2[item]) then
          success += 1
        else
          failures += 1
        end
      end
      return failures
    end