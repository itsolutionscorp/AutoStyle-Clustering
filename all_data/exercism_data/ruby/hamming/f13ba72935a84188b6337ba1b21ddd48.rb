class Hamming
  def self.compute(a, b)
    a_array = a.split('')
    b_array = b.split('')
    sum = 0

    if a_array.length > b_array.length
      b_array.zip(a_array).map! { |x, y| x == y }.each do |val|
        sum += 1 unless val
      end
    else
      a_array.zip(b_array).map! { |x, y| x == y }.each do |val|
        sum += 1 unless val
      end
    end
    sum
  end
end
