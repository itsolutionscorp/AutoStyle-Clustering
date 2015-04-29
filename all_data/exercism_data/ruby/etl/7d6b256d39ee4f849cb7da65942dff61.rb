class ETL
  def self.transform(data)
    hash = Hash.new
    data.each_pair do |key, value| 
      value.map do |v| 
          hash[v.downcase] = key
      end
    end
    hash
  end
end
