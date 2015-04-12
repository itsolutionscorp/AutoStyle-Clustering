def compute(a, b)
    result = 0
    char_count = [a.length, b.length].min
    char_count.times do |i|
      result += 1 if a[i] != b[i]
    end

    result
  end
end