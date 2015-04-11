class ETL
  def self.transform old
    old.each_with_object({}) do |(score, letters), new|
      letters.each { |letter| new[letter.downcase] = score }
    end
  end
end
