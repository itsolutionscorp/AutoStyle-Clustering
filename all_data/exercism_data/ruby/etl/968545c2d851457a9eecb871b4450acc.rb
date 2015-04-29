class ETL

  def self.transform old
    old.each_key.with_object(Hash.new(0)) {|key, result|
      old[key].each {|letter| result[letter.downcase] = key}
    }
  end

end
