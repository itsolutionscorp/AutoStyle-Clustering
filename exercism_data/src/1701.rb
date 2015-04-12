class Hamming
  def compute(a, b)
    a.split('').select.with_index { |c, i| c != b[i] }.size
  end
end
