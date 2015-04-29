class ETL
  class << self
    def transform(old_hash)
      new_hash = {}
      old_hash.each { |key, list| update(new_hash, key, list) }
      new_hash
    end

    def update(new_hash, old_key, old_list)
      old_list.each { |letter| new_hash[letter.downcase] = old_key }
    end
  end
end
