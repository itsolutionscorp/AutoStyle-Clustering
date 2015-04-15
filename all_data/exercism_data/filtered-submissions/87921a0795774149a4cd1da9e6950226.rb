def compute(first, second)
    first.each_char.with_index.count { |c, i| c != second[i] }




  end