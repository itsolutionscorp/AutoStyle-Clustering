class ETL

def self.transform(old)
  array_in_keys_to_individual_keys(old.invert)
end

def self.array_in_keys_to_individual_keys(old)
  transformed = Hash.new
  old.each { |k,v| k.each { |element| transformed[element.downcase] = v } }
  return transformed
end

end
