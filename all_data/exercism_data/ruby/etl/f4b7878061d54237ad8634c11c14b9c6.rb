class ETL
  def self.transform(v1)
    v1.each_with_object({}) do |(points, letters), v2|
      letters.each { |letter| v2[letter.downcase] = points }
    end
  end
end
