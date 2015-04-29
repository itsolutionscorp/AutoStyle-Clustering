class ETL
  def self.transform(old)
    result = {}
    old.map do |number, letters|
      letters.each do |letter|
        result[letter.downcase] = number
      end
    end
    result
  end
end
