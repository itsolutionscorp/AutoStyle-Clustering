class ETL
  def self.transform(input)
    output = {}

    input.each do |p, letters|
      letters.each { |letter| output[letter.downcase] = p }
    end

    output
  end
end
