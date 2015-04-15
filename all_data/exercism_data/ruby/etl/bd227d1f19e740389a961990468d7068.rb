class ETL
  def self.transform(table)
    {}.tap do |hash|
      table.each do |key, array|
        array.each { |value| hash[value.downcase] = key }
      end
    end.sort.to_h
  end
end
