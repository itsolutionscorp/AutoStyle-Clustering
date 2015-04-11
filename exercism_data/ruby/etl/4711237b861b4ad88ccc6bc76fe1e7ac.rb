class ETL

 def self.transform(old)
  new_container = {}
  old.each do |key, value|
    value.flatten.each do |x|
      x = x.to_s.downcase
      new_container[x] = key
    end
  end
  new_container
 end

end
