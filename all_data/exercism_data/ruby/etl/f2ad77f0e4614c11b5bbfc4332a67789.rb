class ETL

  def self.transform(old_scores)
    new_scores = Hash.new()
    old_scores.each { |value, letters| letters.each { |char| new_scores[char.downcase] = value } }
    new_scores
  end

end
