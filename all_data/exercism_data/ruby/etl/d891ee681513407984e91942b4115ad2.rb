class ETL
  def self.transform old
    old.each_with_object({}) do |(key,value), new_hash|
      value.each do |letter|
        new_hash[letter.downcase] = key
      end
    end
  end
end
