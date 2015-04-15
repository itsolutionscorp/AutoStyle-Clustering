class ETL
  def self.transform(old)
    old.inject({}) do |new_map, key_val_pair|
      key = key_val_pair.first
      letters = key_val_pair.last

      letters.each do|letter|
        new_map[letter.downcase] = key
      end
      new_map
    end
  end
end
