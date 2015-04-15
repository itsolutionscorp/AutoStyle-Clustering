class ETL
  def self.transform(old)
    new_hash = {}

    old.each do |k, v|
      v.each do |letter|
        new_hash[letter.downcase] = k
      end
    end
    new_hash
  end
end
