class ETL

  def self.transform(old)
    data = {}
    old.each do |key, value|
      value.each do |value|
        data[value.downcase] = key
      end
    end
    data
  end
end
