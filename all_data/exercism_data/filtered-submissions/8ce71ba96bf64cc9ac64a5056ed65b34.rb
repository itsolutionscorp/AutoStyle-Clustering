def compute(a, b)
    helix1 = a.chars
    helix2 = b.chars

    string_count = [helix1.length, helix2.length].min
    total_count  = 0

    string_count.times do |i|
      total_count += 1 if helix1[i] != helix2[i]
    end

    total_count
  end