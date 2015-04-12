def compute(first, second)
    first.chars.each_with_index.reduce(0) do |a, (e, i)|
      e == second[i] ? a : a + 1
    end
  end