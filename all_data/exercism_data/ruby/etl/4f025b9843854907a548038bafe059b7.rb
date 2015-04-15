class ETL
  def self.transform(data)
    result = {}

    data.each_pair do |value, letters|
      letters.each do |letter|
        result[letter.downcase] = value
      end
    end

    result
  end
end
