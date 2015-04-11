class ETL
  def self.transform(old)
    old.each.with_object({}) do |(score, letters), new|
      letters.each do |letter|
        new[letter.downcase] = score
      end
    end
  end
end
