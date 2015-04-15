class ETL
  def self.transform(s)
    res = {}
    s.each do |k, v|
      v.each { |s| res[s.downcase] = k }
    end
    res
  end
end
