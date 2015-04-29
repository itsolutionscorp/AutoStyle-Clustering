class ETL

  def self.transform(old)
    old.inject({}) do |result,(key,value)|
      value.each {|item| result[item.downcase] = key}
      result
    end
  end

end
