def compute(lhs, rhs)
      diffs = 0
      lhs.length.times do |index|
        if lhs[index] != rhs[index]
          diffs += 1
        end
      end
      diffs
    end