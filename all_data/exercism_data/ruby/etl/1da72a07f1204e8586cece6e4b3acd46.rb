class ETL
  def self.transform(old)
    old.reduce({}) do |result, (number, letters)|
      letters.each { |letter| result[letter.downcase] = number }
      result
    end
  end
end
