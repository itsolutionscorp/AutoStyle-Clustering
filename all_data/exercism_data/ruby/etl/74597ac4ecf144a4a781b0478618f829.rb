class ETL
  def self.transform(input)
    input.each_with_object({}) do |(score, chars), new_scores|
      chars.each { |char| new_scores[char.downcase] = score }
    end
  end
end
