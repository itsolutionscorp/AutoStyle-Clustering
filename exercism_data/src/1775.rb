class Hamming
  def compute(a, b)
    count = 0
    b = b.split('')

    a.split('').each_with_index do |s, i|
      count = count + 1 if b[i] && s != b[i]
    end

    count
  end
end
