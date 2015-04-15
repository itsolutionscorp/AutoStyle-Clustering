class ETL
  def self.transform(old_data)
    new_data = {}

    old_data.each_key do |key|
      old_data[key].each { |value| new_data[value.downcase] = key }
    end

    new_data
  end
end
