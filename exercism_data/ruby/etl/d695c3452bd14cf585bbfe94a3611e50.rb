class ETL
  def self.transform(data)
    data.each_with_object({}) do |(score, letters), memo|
      letters.each { |letter| memo[letter.downcase] = score }
    end
  end
end
