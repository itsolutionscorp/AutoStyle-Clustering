module ETL
  def self.transform(old)
    old.each_with_object({}) do |(value, letters), values|
      letters.each { |letter| values[letter.downcase] = value }
    end
  end
end
