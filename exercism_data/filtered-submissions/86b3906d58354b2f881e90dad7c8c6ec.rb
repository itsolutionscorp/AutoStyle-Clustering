def compute one, the_other
    diffs = 0
    (1..[one, the_other].map(&:length).min).each do |i|
      if one[i-1] != the_other[i-1] then
        diffs += 1
      end
    end
    diffs
  end