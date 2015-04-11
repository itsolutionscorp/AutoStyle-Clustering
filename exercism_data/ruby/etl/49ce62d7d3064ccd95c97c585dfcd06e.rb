module ETL
  def self.transform(old)
    old.each_with_object({}) {|(key,value), hash| value.each { |e|  hash[e.downcase] = key } }
  end
end

class Hash
  include ETL
end
