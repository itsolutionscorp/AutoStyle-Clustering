class ETL

  def self.transform(old)
#    new = {}
#    old.each do |k, v|
#      v.each do |value|
#        new[value.downcase] = k
#      end
#    end
#    new
    old.each_with_object({}) { |i, h| i[1].each { |v| h[v.downcase] = i[0] } }
  end

end
