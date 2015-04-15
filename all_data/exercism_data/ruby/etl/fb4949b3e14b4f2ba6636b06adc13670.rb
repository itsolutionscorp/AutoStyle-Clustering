module ETL
  def self.transform(old_scores)
    old_scores.keys.reduce({}) do |new_scores, key|
      old_scores[key].each do |value|
        new_scores[value.downcase] = key
      end
      new_scores
    end
  end
end
