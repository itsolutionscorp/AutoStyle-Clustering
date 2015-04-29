class ETL
  def self.transform(old_hash)
    ans = old_hash.each_with_object({}) do |(key, value), new_hash|
      value.each {|letter| new_hash[letter.downcase] = key }
    end
  end
end
