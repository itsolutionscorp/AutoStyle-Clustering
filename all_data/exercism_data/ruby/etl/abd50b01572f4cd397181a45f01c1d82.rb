class ETL
  class << self
    def transform(scrabble_letter_score_data)
      scrabble_letter_score_data.each_with_object({}) do |(score, letters), result|
        letters.each{|letter| result[letter.downcase] = score}
      end
    end
  end
end
