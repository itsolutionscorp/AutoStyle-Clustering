class ETL
  def self.transform(data)
    response = {}

    data.each do |number, letters|
      letters.each do |letter|
        response[letter.downcase] = number
      end
    end

    response
  end
end
