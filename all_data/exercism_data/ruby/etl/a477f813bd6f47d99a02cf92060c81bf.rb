class ETL
  def self.transform(old)
    new = {}
    old.each do |key, value|
      value.each do |array_value|
        new[array_value.downcase] = key
      end
    end
    new
  end
end
