class ETL
  def self.transform(hash)
    hash.each.with_object(Hash.new) do |(key, array_of_things), new_hash|
      array_of_things.each do |word|
        new_hash[word.downcase] = key
      end
    end
  end
end
