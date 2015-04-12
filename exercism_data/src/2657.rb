class Hamming
  def compute(a,b)
    counter = 0
    (0..(a.length - 1)).each do |index|
      counter += 1 if a[index] != b[index]
    end
    counter
  end
end
