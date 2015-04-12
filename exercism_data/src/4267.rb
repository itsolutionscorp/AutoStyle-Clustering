class Hamming
  def compute(first, second)
    count = 0
    first.chars.to_a.each_with_index do |c, i|
      count += 1 unless c == second[i]
    end
    count
  end
end
