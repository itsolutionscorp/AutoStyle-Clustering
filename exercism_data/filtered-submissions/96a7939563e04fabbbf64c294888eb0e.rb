def compute(a, b)
    short, long = [a, b].map(&:chars).sort_by &:size

    short.each_with_index.count do |substrand, index|
      long[index] != substrand
    end
  end
end