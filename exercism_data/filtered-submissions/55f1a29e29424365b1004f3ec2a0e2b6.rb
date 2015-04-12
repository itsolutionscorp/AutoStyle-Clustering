class Hamming
  def compute(first, second)
    count = 0
    first.chars.each_with_index do |first_i, i|
      if first_i != second[i] then count += 1; end
    end
    count
  end
end
