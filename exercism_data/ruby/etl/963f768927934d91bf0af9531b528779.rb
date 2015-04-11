class ETL
  def self.transform(old)
    new = {}
    old.each do |k, v|
      v.each do |value|
        new[value.downcase] = k
      end
    end
    new
  end
end
