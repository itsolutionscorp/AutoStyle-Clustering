class ETL
  def self.transform(input)
    input.each_with_object({}) do |(score, letters), output|
      letters.each { |letter| output[letter.downcase] = score }
    end
  end
end
