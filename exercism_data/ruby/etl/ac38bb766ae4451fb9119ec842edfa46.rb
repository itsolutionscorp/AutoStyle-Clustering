class ETL

  def self.transform(data)
    data.each_with_object({}) { |k, o| k[1].each_with_object(o) {|v, o| o[v.downcase] = k[0]} }
  end

end
