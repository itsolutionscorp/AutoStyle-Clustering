class ETL
  def self.transform hash
    res = {}
    
    hash.each do |key, values|
      values.each {|value| res[value.downcase] = key }
    end

    res.sort.to_h
  end
end
