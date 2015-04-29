class ETL
  def self.transform(legacy_data)
    shiny_data = {}

    legacy_data.each do |key, value|
      value.each do |value|
        shiny_data[value.downcase] = key
      end
    end

    shiny_data
  end
end
