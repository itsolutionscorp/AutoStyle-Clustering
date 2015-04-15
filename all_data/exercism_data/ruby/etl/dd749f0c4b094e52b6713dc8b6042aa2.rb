class ETL
  def self.transform(old)
    new_hash = {}
    old.keys.each{|old_key| old[old_key].each { |new_key| new_hash[new_key.downcase] = old_key}}
    new_hash
  end
end
