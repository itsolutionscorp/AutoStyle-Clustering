class ETL
  def self.transform(old)
    old.each_with_object({}) do |(number, letters), result|
      letters.each do |letter|
        result[letter.downcase] = number
      end
    end
  end
end
