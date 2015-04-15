class ETL
  def self.transform(old)
    old.inject({}) do |result, (value, letters)|
      result.merge(Hash[letters.map { |letter| [letter.downcase, value] }])
    end
  end
end
