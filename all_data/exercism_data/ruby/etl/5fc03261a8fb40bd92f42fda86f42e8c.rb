class ETL

  def self.transform(old_format)
    old_format.inject(Hash.new) do |new_format, (score, letters)|
        letters.each { |letter| new_format[letter.downcase] = score }
        new_format
    end
  end
end
