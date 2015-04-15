class ETL
  def self.transform(old)
    new_hash = Hash.new
    old.each do |k, v|
      p k, v
      v.each do |sub_v|
        p sub_v
        new_hash[sub_v.downcase] = k
      end
    end
    new_hash
  end
end
