class ETL
  def self.transform hash
    hash.each_with_object({}) do |(key, value), response|
      value.each{|item| response[item.downcase] =  key }
    end
  end
end
