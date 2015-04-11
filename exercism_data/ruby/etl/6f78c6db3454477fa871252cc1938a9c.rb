class ETL

  def self.transform(old_hsh)
    new_hash = {}

    old_hsh.each do | point, char_array |
      char_array.each{ |char| new_hash[char.downcase] = point }
    end

    new_hash
  end

end
