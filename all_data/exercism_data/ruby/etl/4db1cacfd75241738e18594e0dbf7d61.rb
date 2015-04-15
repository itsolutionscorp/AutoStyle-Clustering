class ETL
  def self.transform(values)
    values.each_with_object(Hash.new) do |(key, value), result|
      value.each {|char| result[char.downcase] = key }
    end
  end
end
