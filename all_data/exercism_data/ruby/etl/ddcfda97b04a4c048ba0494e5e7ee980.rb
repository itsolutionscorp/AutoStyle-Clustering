class ETL

  def self.transform(old_scoring_hash)
    old_scoring_hash.each_with_object({}) do |(score, values), scoring_hash|
      values.each do |value|
        scoring_hash[value.downcase] = score
      end
    end
  end

end
