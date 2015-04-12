class Hamming
  def compute(a, b)
    hamming_count = 0

    a.split('').each_with_index do |char, index|
      break if index == (a.length) || index == (b.length)
      hamming_count += 1 unless b[index] == char
    end

    hamming_count
  end
end
