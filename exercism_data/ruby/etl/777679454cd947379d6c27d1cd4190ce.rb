class ETL
  def self.transform(old)
    old.each.with_object({}) do |(points, letters), new|
      letters.each { |letter| new[letter.downcase] = points }
    end
  end
end
