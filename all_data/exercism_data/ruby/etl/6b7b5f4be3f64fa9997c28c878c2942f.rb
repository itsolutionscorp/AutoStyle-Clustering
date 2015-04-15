class ETL
  def self.transform(old)
    old.inject({}) do |result, (value, letters)|
      hash_from_array = Hash[letters.map { |letter| [letter.downcase, value] }]
      result.merge(hash_from_array)
    end
  end
end
