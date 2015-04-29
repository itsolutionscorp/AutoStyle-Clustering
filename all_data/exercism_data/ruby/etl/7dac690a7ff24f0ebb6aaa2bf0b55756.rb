class ETL
  def self.transform(values)
    result = {}

    values.keys.each do |k|
      values[k].each do |v|
        result[v.downcase] = k
      end
    end

    result
  end
end
