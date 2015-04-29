class ETL

  def self.transform(old_hash)
    new_hash = {}

    old_hash.each do |points, v|
      v.each do |letter|
        new_hash[letter.downcase] = points
      end
    end

    new_hash
  end
end
