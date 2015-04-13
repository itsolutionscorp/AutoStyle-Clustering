def compute(group_one, group_two)


    [group_one.size, group_two.size].min.times.count do |i|
      group_one[i] != group_two[i]
    end
  end