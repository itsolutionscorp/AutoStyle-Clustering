class Hamming
  def compute(a, b)
    a, b = [a, b].sort_by(&:length)

    a = a.split('')
    b = b.split('')

    difference = a.select.with_index { |x, i| x != b[i] }
    difference.size
  end
end
