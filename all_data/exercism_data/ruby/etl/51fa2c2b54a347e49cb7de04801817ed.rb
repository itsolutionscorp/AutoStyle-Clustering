class ETL
  def self.transform(old)
    new = {}
    old.keys.each do |points|
      old[points].each do |letter|
        new[letter.downcase] = points
      end
    end
    new
  end
end
