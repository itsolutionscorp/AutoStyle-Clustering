class ETL

  def self.transform(old)
    transformed = {}

    old.each do |key, value|
      value.each do |v|
        v.downcase!
        transformed[v] = key
      end
    end
    transformed
  end
end
