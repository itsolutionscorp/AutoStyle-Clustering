class ETL
  def self.transform(old_format)
    {}.tap do |new_format|
      old_format.each do |points, letters|
        letters.each do |letter|
          new_format[letter.downcase] = points
        end
      end
    end
  end
end
