class ETL

  def self.transform(old_hash)
    new_hash = {}
    old_hash.each do |data_set|
      data_set[1].each do |new_key|
        new_hash[new_key.downcase] = data_set[0]
      end
    end
    new_hash
  end
end
