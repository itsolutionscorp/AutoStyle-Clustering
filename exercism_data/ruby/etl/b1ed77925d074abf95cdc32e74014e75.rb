class ETL
  def self.transform old
    {}.tap do |new|
      old.each_pair do |key, value|
        value.each do |letter| 
          new[letter.downcase] = key
        end
      end
    end 
  end
end
