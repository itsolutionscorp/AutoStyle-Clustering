class ETL

  def self.transform(data)
    result = data.keys
    result.each_with_object({}){|k,memo| data[k].each{|v| memo[v.downcase!] = k }}
  end

end
