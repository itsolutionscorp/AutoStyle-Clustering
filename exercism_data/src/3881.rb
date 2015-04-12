class Hamming
  def compute(x, y)
    count = 0

    x.chars.zip(y.chars).each do |pair|
      if (pair[1] && pair[0]) && (pair[0] != pair[1])
        count += 1
      end
    end

    count
  end
end
