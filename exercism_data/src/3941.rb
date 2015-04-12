class Hamming

  def compute(a, b)

    diff = 0
    a.each_char.with_index do |char, i|
      break if b[i].nil?
      diff += 1 unless char == b[i]
    end

    return diff

  end

end
