class Hamming
  def compute a, b
    a.split('').zip(b.split('')).inject(0) { |a, e| a + (e.pop == e.pop ? 0 : 1) }
  end
end
