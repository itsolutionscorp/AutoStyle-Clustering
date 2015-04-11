class ETL

  def self.transform(legacy_data)
    new_data = {}
    legacy_data.each do |original_key, list_value|
      list_value.each do |scalar_value|
        new_data[scalar_value.downcase] = original_key
      end
    end
    new_data
  end

end
