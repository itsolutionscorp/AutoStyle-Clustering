class ETL

  def self.transform(old)
    new = {}
    old.each do |key, array|
      array.each do |char|
        new[char.downcase] = key
      end
    end
    new
  end

end
