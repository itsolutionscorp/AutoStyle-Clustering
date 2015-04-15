class ETL

  def self.transform1(legacy)
    new = {}

    legacy.each do |score, letters|
      letters.map!(&:downcase).each { |letter| new[letter] = score }
    end

    new
  end

  def self.transform(legacy)
    legacy.each_with_object({}) do |(score, letters), new|
      letters.map!(&:downcase).each { |letter| new[letter] = score }
    end
  end

end
