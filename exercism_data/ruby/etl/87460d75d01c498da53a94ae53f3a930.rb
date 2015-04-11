class ETL
  def self.transform(old_data)
    old_data.each_with_object({}) do |(score, letters), new_data|
      letters.each do |letter|
        new_data[letter.downcase] = score
      end
    end
  end
end
