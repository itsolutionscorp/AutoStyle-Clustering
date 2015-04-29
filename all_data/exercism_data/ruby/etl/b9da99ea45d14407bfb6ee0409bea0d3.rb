module ETL
  def self.transform(old)
    old.inject({}) do |acc, (value, letters)|
      letters.each { |letter| acc[letter.downcase] = value }
      acc
    end
  end
end
