class ETL

  def self.transform(old_data)
    new_data = {}

    old_data.each do |key, value_array|
      value_array.each do |value|
        value.downcase!
        new_data[value] = key
      end
    end
    new_data
  end
end
