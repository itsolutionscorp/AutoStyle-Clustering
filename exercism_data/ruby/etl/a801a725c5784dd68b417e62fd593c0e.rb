class ETL
  def self.transform(old_lexicon)
    old_lexicon.each_with_object({}) do |(score, letters), new_lexicon|
      letters.each{ |letter| new_lexicon[letter.downcase] = score }
    end
  end
end
