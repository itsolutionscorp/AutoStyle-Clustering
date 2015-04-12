class Hamming

  def compute(a, b)
    count = 0
    if a.length == b.length
      a.split("").each_with_index do |char, index|
        if char != b[index]
          count += 1
        end
      end
    elsif a.length > b.length
      a.slice!(b.length, a.length)
      a.split("").each_with_index do |char, index|
        if char != b[index]
          count += 1
        end
      end
    else
      b.slice!(a.length, b.length)
      b.split("").each_with_index do |char, index|
        if char != a[index]
          count += 1
        end
      end
    end
    count
  end
end
