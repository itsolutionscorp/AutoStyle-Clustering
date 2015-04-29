module ETL
  def self.transform(old)
    res = {}
    old.each do |key, values| 
      values.each do |val| 
        res[val.downcase] = key
      end
    end
    res
  end
end
