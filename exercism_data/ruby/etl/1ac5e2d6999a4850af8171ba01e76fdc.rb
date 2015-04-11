class ETL
  def self.transform(legacy)
    legacy.each_with_object({}) do |old, transformed|
      score, words = old
      words.each { |word| transformed[word.downcase] = score }
    end
  end
end
