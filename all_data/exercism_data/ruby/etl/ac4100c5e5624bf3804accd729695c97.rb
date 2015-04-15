class ETL
  def self.transform(original_data)
    new_data = {}
    original_data.each { |key, values|
      values.each { |word| new_data[word.downcase] = key }
    }
    new_data
  end
end
