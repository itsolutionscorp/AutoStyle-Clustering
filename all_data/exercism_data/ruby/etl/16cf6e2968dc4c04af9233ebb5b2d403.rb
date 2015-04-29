require 'pry'

class ETL
  def self.transform(original_data)
    transformed = {}
    original_data.each { |key, value_array|
      value_array.each { |word| transformed[word.downcase] = key }
    }
    transformed
  end
end
