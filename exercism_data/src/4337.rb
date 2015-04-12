class Hamming

  def compute(first, second)
    count = 0
    length = [first.length, second.length].min

    (0...length).each do |index|
      if first[index] != second[index]
        count = count + 1
      end
    end

    count
  end
end
