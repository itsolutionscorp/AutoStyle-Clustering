class ETL
  def self.transform hash    
    hash.each_with_object({}) do |(score, letters), hash|
      letters.each {|letter| hash[letter.downcase] = score }
    end
  end
end
