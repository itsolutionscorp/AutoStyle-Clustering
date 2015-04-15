class Hamming
  def self.compute(s, t)
    zipped=self.zip_sequences(s, t)
    self.count_unmatching_pairs(zipped)
  end

  private

  def self.zip_sequences(s, t)
    if s.length < t.length
      s.chars.zip t.chars
    else
      t.chars.zip s.chars
    end
  end

  def self.count_unmatching_pairs(zip)
    zip.count { |x, y| x != y }
  end
end
