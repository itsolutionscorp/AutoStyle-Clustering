class Hamming
  def compute(first, second)
    if first.size == second.size
      count = 0
      first.size.times do |index|
        count += 1 if first[index] != second[index]
      end

      return count
    end
  end
end
