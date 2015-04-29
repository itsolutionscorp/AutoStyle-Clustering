class ETL
  def self.transform(old)
    old.each_with_object({}) do |(value, letters), new_score|
      letters.each do |letter|
        new_score[letter.downcase] = value
      end
    end
  end
end
