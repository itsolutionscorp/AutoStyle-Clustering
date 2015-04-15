class ETL

  def self.transform(legacy)
    new = {}

    legacy.each do |score, letters|
      letters.map!(&:downcase).each { |letter| new[letter] = score }
    end

    new
  end

end
