class ETL
  def self.transform(old_data)
    new_data = {}

    old_data.each do |key, values|
      values.each { |value| new_data[value.downcase] = key }
    end

    new_data
  end
end
