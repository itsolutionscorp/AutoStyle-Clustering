class ETL
  def self.transform(old_format)
    old_format.each_with_object({}) do |(score, letters), new_format|
      letters.each do |letter|
        new_format[letter.downcase] = score
      end
    end
  end
end
