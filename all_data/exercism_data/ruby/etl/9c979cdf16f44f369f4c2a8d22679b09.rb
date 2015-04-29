class ETL
  def self.transform old
    old.inject({}) do |result, (value, letters)|
      letters.each do |letter|
        result[letter.downcase] = value
      end
      result
    end
  end
end
