class ETL
  def self.transform(old)
    result = {}
    old.each do |k, v|
      v.each { |oldval| result[oldval.downcase] = k }
    end
    result
  end
end
