class Hamming
  def compute(a, b)
    zipped = a.chars.zip(b.chars)

    distance = 0

    zipped_to_score = zipped.reject {|pair| pair.include?(nil)}

    zipped_to_score.each do |pair|
      distance += 1 if pair.first != pair.last
    end

    distance
  end
end
