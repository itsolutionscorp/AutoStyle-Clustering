class ETL
  def self.transform(old)
    old.each_with_object({}) { |(score, letters), a|
      letters.each { |letter| a[letter.downcase] = score }
    }
  end
end
