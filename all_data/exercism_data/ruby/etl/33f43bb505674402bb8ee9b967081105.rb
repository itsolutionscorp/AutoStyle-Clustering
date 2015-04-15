class ETL
  def self.transform(data)
    {}.tap do |res|
      data.each { |k,v| v.each { |w| res[w.downcase] = k }}
    end
  end
end
