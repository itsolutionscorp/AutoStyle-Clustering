class ETL
  def self.transform(old_format)
    old_format.each_with_object({}) do |(point, words), new_format|
      words.each do |word|
        new_format[word.downcase] = point
      end
    end
  end
end
