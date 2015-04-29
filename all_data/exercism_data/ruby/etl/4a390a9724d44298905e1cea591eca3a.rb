class ETL
  def self.transform(original_data)
    original_data.each_with_object({}) do |(key, values), new_data|
      values.each { |word| new_data[word.downcase] = key }
    end
  end
end
