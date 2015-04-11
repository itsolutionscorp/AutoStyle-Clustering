class ETL

  def self.transform(old_hash)
    reformatted_hash = Hash.new
    switched_hash = old_hash.invert

    switched_hash.keys.each do |keys|
      keys.each do |key_letter|
        reformatted_hash[key_letter.downcase] = switched_hash[keys]
      end
    end
    reformatted_hash
  end
  
end
