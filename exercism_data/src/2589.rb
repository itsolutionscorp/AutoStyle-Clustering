class Hamming
  def compute(first, second)
    count = 0
    s_limit = second.size
    first.split('').each_with_index do |a,i|
      break if s_limit == i
      count += 1 if a != second[i]
    end
    count
  end
end
