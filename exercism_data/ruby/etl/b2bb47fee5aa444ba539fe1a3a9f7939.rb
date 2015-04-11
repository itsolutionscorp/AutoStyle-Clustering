class ETL
  def self.transform(legacy)
    legacy.inject({}) { |scores, value| convert(scores, value) }
  end

  def self.convert(scores, value)
    score, letters = value
    letters.each { |letter| scores[letter.downcase] = score }
    scores
  end
end
