class Hamming
  def self.compute first, second
    (0..first.size-1).map do |i|
      score first[i], second[i]
    end.inject :+
  end

  private

  def self.score char1, char2
    if char1 && char2 && char1 != char2
      1
    else
      0
    end
  end
end
