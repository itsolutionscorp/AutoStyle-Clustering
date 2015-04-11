class ETL

  def self.transform(old_scores)
    old_scores.each_with_object({}) { |(value, letters), new_scores| letters.each { |char| new_scores[char.downcase] = value } }
  end

end
