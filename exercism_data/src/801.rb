class Hamming
  def compute(x, y)
    count = 0
    x.chars.each_with_index do |el, i|
      count+= 1 if y[i] && (y[i] != el)
    end
    count
  end
end
