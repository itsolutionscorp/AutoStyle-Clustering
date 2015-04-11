class ETL
  def self.transform(old)
    old.each_with_object({}) do |(score_key, letters), result|
      letters.each {|letter| result[letter.downcase] = score_key}
    end   
  end
end
