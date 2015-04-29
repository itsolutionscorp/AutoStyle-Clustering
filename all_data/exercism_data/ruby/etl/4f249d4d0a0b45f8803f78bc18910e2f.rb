class ETL
  def self.transform(old)
    new = {}

    old.each do |score, letters|
      letters.each { |letter| new[letter.downcase] = score }
    end

    new
  end
end
