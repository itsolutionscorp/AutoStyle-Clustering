class ETL

  def self.transform(old_format)
    old_format.inject(Hash.new) do |new_format, (score_for_letter, letters)|
        letters.each { |letter| new_format[letter.downcase] = score_for_letter }
        new_format
    end
  end
end
