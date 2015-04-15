class ETL
  def self.transform(h)
    r = {}
    h.each { |key, value| value.each { |v| r[v.downcase] =key } }
    r
  end
end
