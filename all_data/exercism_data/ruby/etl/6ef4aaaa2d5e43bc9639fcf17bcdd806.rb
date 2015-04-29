module ETL
  def self.transform x
    h = {}
    x.each_pair do |key, value|
      value.each { |i| h[i.downcase] = key }
    end
    h
  end
end
